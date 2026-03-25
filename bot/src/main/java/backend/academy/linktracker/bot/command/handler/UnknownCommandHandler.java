package backend.academy.linktracker.bot.command.handler;

import backend.academy.linktracker.bot.command.CommandHandler;
import backend.academy.linktracker.bot.model.CommandType;
import backend.academy.linktracker.bot.model.TelegramUpdateDto;
import backend.academy.linktracker.bot.model.TelegramUpdateResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UnknownCommandHandler implements CommandHandler {
    private static final CommandType commandType = CommandType.UNKNOWN_COMMAND;
    private static final String unknownCommandMessage = String.format("Неизвестная комманда, чтобы посмотреть список доступных комманд, введите %s", CommandType.HELP_COMMAND.getCommandName());

    @Override
    public CommandType getCommandType() {
        return commandType;
    }

    @Override
    public TelegramUpdateResponseDto handle(TelegramUpdateDto updateDto) {
        return new TelegramUpdateResponseDto(updateDto.chatId(), unknownCommandMessage);
    }
}
