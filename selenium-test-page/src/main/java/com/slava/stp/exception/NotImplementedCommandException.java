package com.slava.stp.exception;

import com.slava.stp.Driver.CommandType;
import com.slava.stp.command.Command;

public class NotImplementedCommandException extends SeleniumException {

  public NotImplementedCommandException(Command parentCommand, CommandType type, String name, String className) {
    super("Can't found class command=" + type + name + ", class=" + className + ", parentCommand=" + parentCommand);
  }

  public NotImplementedCommandException(Command command) {
    super("Not implemented command: " + command);
  }
}
