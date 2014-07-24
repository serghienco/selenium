package com.slava.stp.exception;

public class ValueFieldMustBeEmpty extends SeleniumException {

  public ValueFieldMustBeEmpty(Object o) {
    super("Value field must be empty: " + o);
  }
}
