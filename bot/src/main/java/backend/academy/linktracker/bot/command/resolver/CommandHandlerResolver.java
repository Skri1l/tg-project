package backend.academy.linktracker.bot.command.resolver;

import backend.academy.linktracker.bot.command.CommandHandler;
import backend.academy.linktracker.bot.model.CommandType;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class CommandHandlerResolver {
    private final Map<CommandType, CommandHandler> handlerMap;

    public CommandHandlerResolver(List<CommandHandler> handlers) {
        this.handlerMap = handlers.stream()
            .collect(Collectors.toMap(
                CommandHandler::getCommandType,
                Function.identity()
            ));
    }

    public CommandHandler resolve(CommandType commandType) {
        return handlerMap.get(commandType);
    }
}
