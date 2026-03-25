package backend.academy.linktracker.bot.command.handler;

import backend.academy.linktracker.bot.command.CommandHandler;
import backend.academy.linktracker.bot.model.CommandType;
import backend.academy.linktracker.bot.model.TelegramUpdateDto;
import backend.academy.linktracker.bot.model.TelegramUpdateResponseDto;
import org.springframework.stereotype.Component;

@Component
public class StartCommandHandler implements CommandHandler {

    private static final CommandType commandType = CommandType.START_COMMAND;
    private static final String startMessage = String.format("Добро пожаловать! Используйте %s, чтобы посмотреть доступные команды.", CommandType.HELP_COMMAND.getCommandName());

    @Override
    public CommandType getCommandType() {
        return commandType;
    }

    @Override
    public TelegramUpdateResponseDto handle(TelegramUpdateDto updateDto) {
        return new TelegramUpdateResponseDto(updateDto.chatId(), startMessage);
    }
}
