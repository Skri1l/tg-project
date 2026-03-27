package backend.academy.linktracker.bot.listener;

import backend.academy.linktracker.bot.mapper.TelegramMessageMapper;
import backend.academy.linktracker.bot.model.TelegramUpdateDto;
import backend.academy.linktracker.bot.model.TelegramUpdateResponseDto;
import backend.academy.linktracker.bot.sender.TelegramMessageSender;
import backend.academy.linktracker.bot.service.BotService;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BotListener implements UpdatesListener {
    private final TelegramBot telegramBot;
    private final BotService botService;
    private final TelegramMessageMapper messageMapper;
    private final TelegramMessageSender messageSender;

    @PostConstruct
    public void start() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updateList) {
        Integer lastProcessedId = CONFIRMED_UPDATES_NONE;
        List<TelegramUpdateResponseDto> responses = new ArrayList<>(updateList.size());
        try {
            for (final var telegramMessage : updateList) {
                lastProcessedId = telegramMessage.updateId();
                responses.add(botService.processUpdate(telegramMessage));
            }
        } catch (Exception e) {
            log.atInfo()
                .addArgument(lastProcessedId)
                .log("Failed to process update with updateId ${}");
            return lastProcessedId;
        }

        responses.forEach(messageSender::sendMessage);

        return CONFIRMED_UPDATES_ALL;
    }
}
