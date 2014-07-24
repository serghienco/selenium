package com.slava.stp.exception;

public class SeleniumException extends RuntimeException {

  public SeleniumException() {
  }

  public SeleniumException(String paramString) {
    super(paramString);
  }

  public SeleniumException(String paramString, Throwable paramThrowable) {
    super(paramString, paramThrowable);
  }

  public SeleniumException(Throwable paramThrowable) {
    super(paramThrowable);
  }
}
