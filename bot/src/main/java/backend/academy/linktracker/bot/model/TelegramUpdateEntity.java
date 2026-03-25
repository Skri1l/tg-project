package backend.academy.linktracker.bot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class TelegramUpdateEntity {
    Long id;
    Integer updateId;
    Long chatId;
    Long userId;
    String username;
    String message;
}

