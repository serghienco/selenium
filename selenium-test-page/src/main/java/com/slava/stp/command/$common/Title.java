package com.slava.stp.command.$common;

public class Title extends CommonAbstract {

  @Override
  public Object execute() {
    return getDriver().getTitle();
  }

  @Override
  public String getValueOrTargetHelp() {
    return getTarget();
  }
}
