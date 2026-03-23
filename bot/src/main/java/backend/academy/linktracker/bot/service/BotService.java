package backend.academy.linktracker.bot.service;

import backend.academy.linktracker.bot.model.CommandType;
import backend.academy.linktracker.bot.model.EventType;
import backend.academy.linktracker.bot.model.TelegramUpdateDto;
import backend.academy.linktracker.bot.model.TelegramUpdateResponseDto;
import backend.academy.linktracker.bot.repository.BotRepository;
import java.util.Arrays;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

// TODO: divide logic next way:
//  1) BotService should only work with TelegramUpdateEntity
//  2) Handlers should handle messages
//  3) Must have one more class which decides which handler is needed or put this logic into listener
@Service
public class BotService {
    private final BotRepository botRepository;
    private final Map<CommandType, >
    private static final Logger log = LoggerFactory.getLogger(BotService.class);

    private final static Map<CommandType, EventType> commandTypeToEventTypeMap = Map.of(
        CommandType.START_COMMAND, EventType.START_EVENT,
        CommandType.HELP_COMMAND, EventType.HELP_EVENT
    );

    // TODO: extract logging
    public TelegramUpdateResponseDto processUpdate(final TelegramUpdateDto requestDto){
        final Long chatId = requestDto.chatId();
        final Long userId = requestDto.userId();
        final String message = requestDto.message().toLowerCase();
        final String username = requestDto.username();

        log.atInfo()
            .addArgument(EventType.MESSAGE_RECEIVED)
            .addArgument(chatId)
            .addArgument(message)
            .addArgument(userId)
            .addArgument(username)
            .log("event = {} chatId={} message={} userId={} username={}");

        CommandType commandType = Arrays.stream(CommandType.values())
            .filter(cmd -> message.startsWith(cmd.getCommandName()))
            .findFirst()
            .orElse(null);

        log.atInfo()
            .addArgument(commandTypeToEventTypeMap.get(commandType))
            .addArgument(chatId)
            .addArgument(userId)
            .addArgument(username)
            .log("event = {} chatId={}, userId={}, username={}");

        return new TelegramUpdateResponseDto("Добро пожаловать! Используйте /help, чтобы посмотреть доступные команды.");

        return new TelegramUpdateResponseDto("Список доступных команд: \n/start, \n/help");
    }
}
