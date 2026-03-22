package backend.academy.linktracker.bot.model.dto;

public record TelegramMessageRequestDto(
    Integer updateId,
    Long chatId,
    Long userId,
    String username,
    String message
) {}
