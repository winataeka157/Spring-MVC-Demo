package com.example.bliblispringdemo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Aspect
@Slf4j
@Component
public class CommandLoggingAspect {

  @Before("execution(* com.example.bliblispringdemo.command.Command.execute(..))")
  public void logBeforeExecute(JoinPoint joinPoint) {
    log.info("Executing %s with args: %s".formatted(
      joinPoint.getSignature().getName(),
      getCommandRequestString(joinPoint.getArgs())));
  }

  @AfterReturning(pointcut = "execution(* com.example.bliblispringdemo.command.Command.execute(..))", returning = "result")
  public void logAfterExecute(JoinPoint joinPoint, Object result) {
    log.info("Successfully executed %s with result: %s".formatted(
      joinPoint.getSignature().getName(),
      result));
  }

  private String getCommandRequestString(Object[] objects) {
    return Stream.of(objects)
      .map(Object::toString)
      .collect(Collectors.joining(", "));
  }

}
