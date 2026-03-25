package backend.academy.linktracker.bot.validation;

import backend.academy.linktracker.bot.model.TelegramUpdateProperty;
import com.pengrad.telegrambot.model.Update;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UpdateValidator {
    public boolean validate(Update update) {
        List<TelegramUpdateProperty<?>> propertiesToValidate = List.of(
            new TelegramUpdateProperty<>("message", update.message()),
            new TelegramUpdateProperty<>("user", update.message().from()),
            new TelegramUpdateProperty<>("username", update.message().from().username()),
            new TelegramUpdateProperty<>("messageText", update.message().text())
        );

        return propertiesToValidate.stream()
            .allMatch(property -> validateUpdateProperty(
                update.updateId(),
                property
            ));
    }

    private <T> boolean validateUpdateProperty(int updateId, TelegramUpdateProperty<T> property) {
        if (property.value() == null) {
            log.atWarn()
                .addArgument(updateId)
                .addArgument(property.name())
                .log("Update ${} has null property ${}");
            return false;
        }

        return true;
    }
}
