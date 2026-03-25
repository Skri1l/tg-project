package backend.academy.linktracker.bot.service;

import backend.academy.linktracker.bot.command.CommandHandler;
import backend.academy.linktracker.bot.command.resolver.CommandHandlerResolver;
import backend.academy.linktracker.bot.model.CommandType;
import backend.academy.linktracker.bot.model.TelegramUpdateDto;
import backend.academy.linktracker.bot.model.TelegramUpdateResponseDto;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BotService {
    private final CommandHandlerResolver commandHandlerResolver;

    public TelegramUpdateResponseDto processUpdate(final TelegramUpdateDto requestDto) {
        final CommandType commandType = Arrays.stream(CommandType.values())
            .filter(cmd -> !cmd.equals(CommandType.UNKNOWN_COMMAND))
            .filter(cmd -> requestDto.message().startsWith(cmd.getCommandName()))
            .findFirst()
            .orElse(CommandType.UNKNOWN_COMMAND);

        CommandHandler commandHandler = commandHandlerResolver.resolve(commandType);
        TelegramUpdateResponseDto response = commandHandler.handle(requestDto);

        return response;
    }
}
