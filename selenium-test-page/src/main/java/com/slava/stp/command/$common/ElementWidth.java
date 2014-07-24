package com.slava.stp.command.$common;

public class ElementWidth extends CommonAbstract {

  @Override
  public Object execute() {
    return getElementByLocatorTarget().getSize().getWidth();
  }

  @Override
  public String getValueOrTargetHelp() {
    return getValue();
  }
}
