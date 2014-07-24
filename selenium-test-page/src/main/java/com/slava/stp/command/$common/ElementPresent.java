package com.slava.stp.command.$common;

import org.openqa.selenium.NoSuchElementException;

public class ElementPresent extends CommonAbstract {

  @Override
  public Object execute() {
    try {
      getElementByLocatorTarget();
      return Boolean.TRUE;
    } catch (NoSuchElementException e) {
      return Boolean.FALSE;
    }
  }

  @Override
  public String getValueOrTargetHelp() {
    return getValue();
  }
}
