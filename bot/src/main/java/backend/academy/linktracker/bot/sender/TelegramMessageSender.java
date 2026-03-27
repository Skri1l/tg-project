package backend.academy.linktracker.bot.sender;

import backend.academy.linktracker.bot.model.TelegramUpdateResponseDto;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TelegramMessageSender {

    private final TelegramBot telegramBot;

    public void sendMessage(TelegramUpdateResponseDto dto) {
        SendMessage message = new SendMessage(
            dto.chatId(),
            dto.message()
        );

        telegramBot.execute(message);
    }
}
