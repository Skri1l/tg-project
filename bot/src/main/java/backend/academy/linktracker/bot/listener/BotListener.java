package backend.academy.linktracker.bot.listener;

import backend.academy.linktracker.bot.mapper.TelegramMessageMapper;
import backend.academy.linktracker.bot.model.dto.TelegramMessageRequestDto;
import backend.academy.linktracker.bot.service.BotService;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class BotListener implements UpdatesListener {
    private final TelegramBot telegramBot;
    private final BotService botService;
    private final TelegramMessageMapper messageMapper;

    @PostConstruct
    public void start() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updateList) {
        List<TelegramMessageRequestDto> telegramMessageDtoList = messageMapper.toDtoList(updateList);

        Integer lastProcessedId = CONFIRMED_UPDATES_NONE;
        try {
            for(final var telegramMessage : telegramMessageDtoList) {
                lastProcessedId = telegramMessage.updateId();
                // TODO: add response handling
                botService.processMessage(telegramMessage);
            }
        } catch (Exception e) {
            log.atInfo()
                .addArgument(lastProcessedId)
                .log("Failed to process update with updateId ${}");
            return lastProcessedId;
        }

        return CONFIRMED_UPDATES_ALL;
    }
}
