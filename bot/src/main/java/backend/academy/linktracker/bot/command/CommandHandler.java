package backend.academy.linktracker.bot.command;

import backend.academy.linktracker.bot.model.CommandType;
import backend.academy.linktracker.bot.model.TelegramUpdateDto;
import backend.academy.linktracker.bot.model.TelegramUpdateResponseDto;

public interface CommandHandler {
    CommandType getCommandType();
    TelegramUpdateResponseDto handle(TelegramUpdateDto telegramUpdateDto);
}
