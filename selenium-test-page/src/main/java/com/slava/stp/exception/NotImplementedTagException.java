package com.slava.stp.exception;

public class NotImplementedTagException extends SeleniumException {

  public NotImplementedTagException(String e) {
    super("Are not implemented " + e);
  }
}
