package com.example.bliblispringdemo.base;

public interface Command<R, T> {

  T execute(R request);

}
