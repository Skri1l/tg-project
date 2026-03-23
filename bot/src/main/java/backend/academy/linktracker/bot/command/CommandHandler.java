package backend.academy.linktracker.bot.command;

import backend.academy.linktracker.bot.model.TelegramUpdateDto;
import backend.academy.linktracker.bot.model.TelegramUpdateResponseDto;

public interface CommandHandler {
    TelegramUpdateResponseDto handle(TelegramUpdateDto telegramUpdateDto);
}
