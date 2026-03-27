package backend.academy.linktracker.bot.service;

import backend.academy.linktracker.bot.command.CommandHandler;
import backend.academy.linktracker.bot.command.resolver.CommandHandlerResolver;
import backend.academy.linktracker.bot.mapper.TelegramMessageMapper;
import backend.academy.linktracker.bot.model.CommandType;
import backend.academy.linktracker.bot.model.TelegramUpdateDto;
import backend.academy.linktracker.bot.model.TelegramUpdateResponseDto;
import backend.academy.linktracker.bot.repository.BotRepository;
import backend.academy.linktracker.bot.validation.UpdateValidator;
import java.util.Arrays;
import com.pengrad.telegrambot.model.Update;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BotService {
    private final BotRepository botRepository;
    private final TelegramMessageMapper telegramMessageMapper;
    private final CommandHandlerResolver commandHandlerResolver;
    private final UpdateValidator updateValidator;

    public TelegramUpdateResponseDto processUpdate(final Update update) {
        updateValidator.validate(update);
        final TelegramUpdateDto updateRequestDto = telegramMessageMapper.toDtoFromUpdate(update);

        final CommandType commandType = Arrays.stream(CommandType.values())
            .filter(cmd -> !cmd.equals(CommandType.UNKNOWN_COMMAND))
            .filter(cmd -> updateRequestDto.message().startsWith(cmd.getCommandName()))
            .findFirst()
            .orElse(CommandType.UNKNOWN_COMMAND);

        CommandHandler commandHandler = commandHandlerResolver.resolve(commandType);
        botRepository.save(telegramMessageMapper.toEntity(updateRequestDto));
        TelegramUpdateResponseDto response = commandHandler.handle(updateRequestDto);

        return response;
    }
}
