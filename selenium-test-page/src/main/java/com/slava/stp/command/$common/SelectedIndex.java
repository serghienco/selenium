package com.slava.stp.command.$common;

public class SelectedIndex extends CommonAbstract {

  @Override
  public Object execute() {
    return getSelectElementByLocatorTarget().getFirstSelectedOptionProperty("index");
  }

  @Override
  public String getValueOrTargetHelp() {
    return getValue();
  }
}
