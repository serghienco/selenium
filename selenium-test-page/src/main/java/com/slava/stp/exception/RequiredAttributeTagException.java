package com.slava.stp.exception;

public class RequiredAttributeTagException extends SeleniumException {

  public RequiredAttributeTagException(String name) {
    super("tag attribute " + name + " is required");
  }

  public RequiredAttributeTagException() {
  }
}
