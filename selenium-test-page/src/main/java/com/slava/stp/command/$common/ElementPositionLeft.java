package com.slava.stp.command.$common;

public class ElementPositionLeft extends CommonAbstract {

  @Override
  public Object execute() {
    return getElementByLocatorTarget().getLocation().getX();
  }

  @Override
  public String getValueOrTargetHelp() {
    return getValue();
  }
}
