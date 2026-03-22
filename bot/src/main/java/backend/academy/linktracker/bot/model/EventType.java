package backend.academy.linktracker.bot.model;

public enum EventType {
    MESSAGE_RECEIVED("message_received"),
    START_EVENT("start_event"),
    HELP_EVENT("help_event");

    private final String eventName;

    public String getEventName() {
        return eventName;
    }

    EventType(String eventName) {
        this.eventName = eventName;
    }
}
