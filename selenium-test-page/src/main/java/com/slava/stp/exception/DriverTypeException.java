package com.slava.stp.exception;

public class DriverTypeException extends SeleniumException {

  public DriverTypeException(String name) {
    super("Driver \"" + name + "\" don't suport.");
  }
}
