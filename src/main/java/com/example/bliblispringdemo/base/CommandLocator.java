package com.example.bliblispringdemo.base;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CommandLocator {

  private final List<Command<?, ?>> commands;

  public <R, T, C extends Command<R, T>> Command<R, T> getCommand(Class<C> clazz) {
    return commands.stream()
      .filter(clazz::isInstance)
      .findFirst()
      .map(command -> (C) command)
      .orElseThrow(() -> new RuntimeException("Command Not found"));
  }

}
