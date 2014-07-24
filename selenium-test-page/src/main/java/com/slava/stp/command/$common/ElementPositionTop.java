package com.slava.stp.command.$common;

public class ElementPositionTop extends CommonAbstract {

  @Override
  public Object execute() {
    return getElementByLocatorTarget().getLocation().getY();
  }

  @Override
  public String getValueOrTargetHelp() {
    return getValue();
  }
}
