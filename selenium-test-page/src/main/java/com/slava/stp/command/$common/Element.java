package com.slava.stp.command.$common;

public class Element extends CommonAbstract {

  @Override
  public Object execute() {
    return getElementByLocatorTarget();
  }

  @Override
  public String getValueOrTargetHelp() {
    return getValue();
  }

}
