package backend.academy.linktracker.bot.model;

public record TelegramUpdateProperty<T>(
    String name,
    T value
) { }
