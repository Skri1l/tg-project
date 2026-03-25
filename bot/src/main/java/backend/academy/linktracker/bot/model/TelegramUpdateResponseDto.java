package backend.academy.linktracker.bot.model;

public record TelegramUpdateResponseDto(
    Long chatId,
    String message
) { }
