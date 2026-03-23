package backend.academy.linktracker.bot.model;

public record TelegramUpdateDto(
    Integer updateId,
    Long chatId,
    Long userId,
    String username,
    String message
) {}
