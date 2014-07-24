package com.slava.stp.command.$common;

public class Checked extends CommonAbstract {

  @Override
  public Object execute() {
    return getElementByLocatorTarget().isSelected();
  }

  @Override
  public String getValueOrTargetHelp() {
    return getValue();
  }
}
