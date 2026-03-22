package backend.academy.linktracker.bot.validation;

import backend.academy.linktracker.bot.model.TelegramMessageProperty;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.List;

@Slf4j
@Component
public class UpdateValidator {
    public boolean validate(Update update) {
        List<TelegramMessageProperty<?>> propertiesToValidate = List.of(
            new TelegramMessageProperty<>("message", update.message()),
            new TelegramMessageProperty<>("user", update.message().from()),
            new TelegramMessageProperty<>("username", update.message().from().username()),
            new TelegramMessageProperty<>("messageText", update.message().text())
        );

        return propertiesToValidate.stream()
            .allMatch(property -> validateUpdateProperty(
                update.updateId(),
                property
            ));
    }

    private <T> boolean validateUpdateProperty(int updateId, TelegramMessageProperty<T> property) {
        if(property.value() == null){
            log.atWarn()
                .addArgument(updateId)
                .addArgument(property.name())
                .log("Update ${} has null property ${}");
            return false;
        }

        return true;
    }
}
