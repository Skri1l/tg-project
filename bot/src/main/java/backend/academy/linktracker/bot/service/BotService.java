package backend.academy.linktracker.bot.service;

import backend.academy.linktracker.bot.model.CommandType;
import backend.academy.linktracker.bot.model.EventType;
import backend.academy.linktracker.bot.model.dto.TelegramMessageRequestDto;
import backend.academy.linktracker.bot.model.dto.TelegramMessageResponseDto;
import backend.academy.linktracker.bot.repository.BotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Arrays;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BotService {
    private final BotRepository botRepository;
    private static final Logger log = LoggerFactory.getLogger(BotService.class);

    private final static Map<CommandType, EventType> commandTypeToEventTypeMap = Map.of(
        CommandType.START_COMMAND, EventType.START_EVENT,
        CommandType.HELP_COMMAND, EventType.HELP_EVENT
    );

    // TODO: extract logging
    public TelegramMessageResponseDto processMessage(final TelegramMessageRequestDto requestDto){
        final Long chatId = requestDto.chatId();
        final Long userId = requestDto.userId();
        final String message = requestDto.message();
        final String username = requestDto.username();

        log.atInfo()
            .addArgument(EventType.MESSAGE_RECEIVED)
            .addArgument(chatId)
            .addArgument(message)
            .addArgument(userId)
            .addArgument(username)
            .log("event = {} chatId={} message={} userId={} username={}");

        CommandType commandType = Arrays.stream(CommandType.values())
            .filter(cmd -> cmd.getCommandName().equals(message))
            .findFirst()
            .orElse(null);

        log.atInfo()
            .addArgument(commandTypeToEventTypeMap.get(commandType))
            .addArgument(chatId)
            .addArgument(userId)
            .addArgument(username)
            .log("event = {} chatId={}, userId={}, username={}");

            return new TelegramMessageResponseDto("Добро пожаловать! Используйте /help, чтобы посмотреть доступные команды.");

            return new TelegramMessageResponseDto("Список доступных команд: \n/start, \n/help")

        return ;
    }
}
