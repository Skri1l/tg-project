package backend.academy.linktracker.bot.mapper;

import backend.academy.linktracker.bot.model.TelegramUpdateDto;
import backend.academy.linktracker.bot.validation.UpdateValidator;
import com.pengrad.telegrambot.model.Update;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TelegramMessageMapper {
    private final UpdateValidator updateValidator;

    public TelegramUpdateDto toEntity(Update update) {
        return new TelegramUpdateDto(
            update.updateId(),
            update.message().chat().id(),
            update.message().from().id(),
            update.message().from().username(),
            update.message().text()
        );
    }

    public

    public List<TelegramUpdateDto> toDtoList(List<Update> updateList) {
        return updateList.stream()
            .filter(updateValidator::validate)
            .map(this::toDto)
            .toList();
    }
}
