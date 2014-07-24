package com.slava.stp.command.$common;

public class ElementHeight extends CommonAbstract {

  @Override
  public Object execute() {
    return getElementByLocatorTarget().getSize().getHeight();
  }

  @Override
  public String getValueOrTargetHelp() {
    return getValue();
  }
}
