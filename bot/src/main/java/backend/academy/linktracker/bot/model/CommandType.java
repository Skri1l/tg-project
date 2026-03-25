package backend.academy.linktracker.bot.model;

public enum CommandType {
    START_COMMAND("/start"),
    HELP_COMMAND("/help"),
    UNKNOWN_COMMAND("UNKNOWN_COMMAND");

    private final String commandName;

    public String getCommandName() {
        return commandName;
    }

    CommandType(String commandName) {
        this.commandName = commandName;
    }
}
