package com.slava.stp.exception;

public class DeprecatedException extends SeleniumException {

  public DeprecatedException(String e) {
    super("Has been deprecated " + e);
  }
}
