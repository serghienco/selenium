package com.slava.stp.command.$common;

public class Text extends CommonAbstract {

  @Override
  public Object execute() {
    return getElementByLocatorTarget().getText();
  }

  @Override
  public String getValueOrTargetHelp() {
    return getValue();
  }
}
