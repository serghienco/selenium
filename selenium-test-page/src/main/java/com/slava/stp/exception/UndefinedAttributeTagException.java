package com.slava.stp.exception;

import com.slava.stp.tagbuilder.ParameterName;

public class UndefinedAttributeTagException extends SeleniumException {

  public UndefinedAttributeTagException(ParameterName name) {
    super("undefined attribute [" + name + "]");
  }
}
