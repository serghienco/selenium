package com.slava.stp.command.$common;

public class SelectedValue extends CommonAbstract {

  @Override
  public Object execute() {
    return getSelectElementByLocatorTarget().getFirstSelectedOptionProperty("value");
  }

  @Override
  public String getValueOrTargetHelp() {
    return getValue();
  }
}
