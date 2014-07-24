package com.slava.stp.exception;

import com.slava.stp.Driver.CommandType;

public class CommandInitiateException extends RuntimeException {

  public CommandInitiateException(CommandType type, String name, String className) {
    super("Can't initiate command=" + type + name + ", class=" + className);
  }
}
