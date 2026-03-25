package backend.academy.linktracker.bot.mapper;

import backend.academy.linktracker.bot.model.TelegramUpdateDto;
import backend.academy.linktracker.bot.model.TelegramUpdateEntity;
import backend.academy.linktracker.bot.validation.UpdateValidator;
import com.pengrad.telegrambot.model.Update;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TelegramMessageMapper {
    private final UpdateValidator updateValidator;

    public TelegramUpdateDto toDto(Update update) {
        return new TelegramUpdateDto(
            update.updateId(),
            update.message().chat().id(),
            update.message().from().id(),
            update.message().from().username(),
            update.message().text()
        );
    }

    public TelegramUpdateEntity toEntity(TelegramUpdateDto update) {
        return TelegramUpdateEntity.builder()
            .updateId(update.updateId())
            .chatId(update.chatId())
            .userId(update.userId())
            .username(update.username())
            .message(update.message())
            .build();
    }

    public List<TelegramUpdateDto> toDtoList(List<Update> updateList) {
        return updateList.stream()
            .filter(updateValidator::validate)
            .map(this::toDto)
            .toList();
    }
}
