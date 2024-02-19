package com.example.bliblispringdemo.command;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommandExecutor {

  private final ApplicationContext applicationContext;

  public <R, T, C extends Command<R,T>> T execute(Class<C> clazz, R request) {
    return applicationContext.getBean(clazz)
      .execute(request);
  }

}
