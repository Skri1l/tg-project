package backend.academy.linktracker.bot.aop.logging;

import backend.academy.linktracker.bot.command.CommandHandler;
import backend.academy.linktracker.bot.model.TelegramUpdateDto;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class CommandLoggingAspect {
    @Before("execution(* backend.academy..*CommandHandler.handle(..))")
    public void logBeforeMethod(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args.length == 0) return;

        Object targetObject = joinPoint.getTarget();
        if (targetObject == null) {
            log.atWarn()
                .log("Unable to log! Unable to receive CommandHandler object!");
            return;
        }
        CommandHandler handler = (CommandHandler) targetObject;

        Object param = args[0];
        if (param == null) {
            log.atWarn()
                .log("Unable to log! Unable to receive parameter TelegramUpdateDto object!");
            return;
        }
        TelegramUpdateDto update = (TelegramUpdateDto) param;

        log.atInfo()
            .addArgument(handler.getCommandType())
            .addArgument(update.chatId())
            .addArgument(update.userId())
            .addArgument(update.username())
            .log("event = {} chatId={}, userId={}, username={}");
    }
}
