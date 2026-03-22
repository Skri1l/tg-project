package backend.academy.linktracker.bot.model;

public record TelegramMessageProperty<T>(
    String name,
    T value
) { }
