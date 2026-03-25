package backend.academy.linktracker.bot.command.handler;

import backend.academy.linktracker.bot.command.CommandHandler;
import backend.academy.linktracker.bot.model.CommandType;
import backend.academy.linktracker.bot.model.TelegramUpdateDto;
import backend.academy.linktracker.bot.model.TelegramUpdateResponseDto;
import java.util.Arrays;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class HelpCommandHandler implements CommandHandler {
    private static final CommandType commandType = CommandType.HELP_COMMAND;
    private static String helpMessage;

    public HelpCommandHandler() {
        helpMessage = Arrays.stream(CommandType.values())
            .map(CommandType::getCommandName)
            .collect(Collectors.joining("\n", "Вот список доступных команд: \n", ""));
    }

    @Override
    public CommandType getCommandType() {
        return commandType;
    }

    @Override
    public TelegramUpdateResponseDto handle(TelegramUpdateDto updateDto) {
        return new TelegramUpdateResponseDto(updateDto.chatId(), helpMessage);
    }
}
