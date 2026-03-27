package backend.academy.linktracker.bot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Setter
@Getter
@Builder
public class TelegramUpdateEntity {
    private Long id;
    private Integer updateId;
    private Long chatId;
    private Long userId;
    private String username;
    private String message;
}

