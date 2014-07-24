package com.slava.stp.command.$common;

public class SelectedId extends CommonAbstract {

  @Override
  public Object execute() {
    return getSelectElementByLocatorTarget().getFirstSelectedOptionProperty("id");
  }

  @Override
  public String getValueOrTargetHelp() {
    return getValue();
  }
}
