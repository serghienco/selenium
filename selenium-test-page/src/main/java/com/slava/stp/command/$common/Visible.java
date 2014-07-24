package com.slava.stp.command.$common;

public class Visible extends CommonAbstract {

  @Override
  public Object execute() {
    return getElementByLocatorTarget().isDisplayed();
  }

  @Override
  public String getValueOrTargetHelp() {
    return getValue();
  }
}
