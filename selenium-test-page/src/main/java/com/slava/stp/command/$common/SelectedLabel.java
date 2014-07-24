package com.slava.stp.command.$common;

public class SelectedLabel extends CommonAbstract {

  @Override
  public Object execute() {
    return getSelectElementByLocatorTarget().getFirstSelectedOptionProperty("text");
  }

  @Override
  public String getValueOrTargetHelp() {
    return getValue();
  }
}