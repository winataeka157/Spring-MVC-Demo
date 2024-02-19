package com.example.bliblispringdemo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Aspect
@Slf4j
@Component
public class CommandLoggingAspect {

  @Pointcut("execution(* com.example.bliblispringdemo.command.Command.execute(..))")
  public void executeCommandPointcut() {}

  @Before("executeCommandPointcut()")
  public void logBeforeExecute(JoinPoint joinPoint) {
    log.info("%s executing %s with args: %s".formatted(
      getCommandName(joinPoint),
      joinPoint.getSignature().getName(),
      getCommandRequestString(joinPoint.getArgs())));
  }

  @AfterReturning(pointcut = "executeCommandPointcut()", returning = "result")
  public void logAfterExecute(JoinPoint joinPoint, Object result) {
    log.info("%s successfully executed %s with result: %s".formatted(
      getCommandName(joinPoint),
      joinPoint.getSignature().getName(),
      result));
  }

  @AfterThrowing(pointcut = "executeCommandPointcut()", throwing = "throwable")
  public void logAfterExecuteThrow(JoinPoint joinPoint, Throwable throwable) {
    log.error("%s failed to executed with error:".formatted(getCommandName(joinPoint)), throwable.getMessage());
  }

  private String getCommandName(JoinPoint joinPoint) {
    return "#%s".formatted(joinPoint.getTarget().getClass().getSimpleName());
  }

  private String getCommandRequestString(Object[] objects) {
    return Stream.of(objects)
      .map(Object::toString)
      .collect(Collectors.joining(", "));
  }

}
