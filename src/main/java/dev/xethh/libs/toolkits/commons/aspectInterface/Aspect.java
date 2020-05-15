package dev.xethh.libs.toolkits.commons.aspectInterface;

import dev.xethh.libs.toolkits.commons.logging.WithLogger;
import org.aspectj.lang.ProceedingJoinPoint;

public abstract class Aspect implements WithLogger {
    public abstract Object executeTask(ProceedingJoinPoint joinPoint) throws Throwable;
    public abstract Object execute(ProceedingJoinPoint joinPoint) throws Throwable;
}
