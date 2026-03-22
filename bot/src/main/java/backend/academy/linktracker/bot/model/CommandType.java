package backend.academy.linktracker.bot.model;

public enum CommandType {
    START_COMMAND("/start"),
    HELP_COMMAND("/help");

    private final String commandName;

    public String getCommandName() {
        return commandName;
    }

    CommandType(String commandName) {
        this.commandName = commandName;
    }
}
