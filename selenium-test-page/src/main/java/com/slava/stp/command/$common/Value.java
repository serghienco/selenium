package com.slava.stp.command.$common;

public class Value extends CommonAbstract {

  @Override
  public Object execute() {
    return getElementByLocatorTarget().getAttribute("value");
  }

  @Override
  public String getValueOrTargetHelp() {
    return getValue();
  }
}
