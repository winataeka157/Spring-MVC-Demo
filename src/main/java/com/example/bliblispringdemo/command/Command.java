package com.example.bliblispringdemo.command;

public interface Command<R, T> {

  T execute(R request);

}
