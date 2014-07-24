package com.slava.stp.command.$common;

public class Location extends CommonAbstract {

  @Override
  public Object execute() {
    return getDriver().getCurrentUrl();
  }

  @Override
  public String getValueOrTargetHelp() {
    return getTarget();
  }

}
