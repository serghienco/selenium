package com.slava.stp.command.$common;

public class Alert extends CommonAbstract {

  @Override
  public Object execute() {
    return getAlertOverride().getNextAlert(getDriver());
  }

  @Override
  public String getValueOrTargetHelp() {
    return getTarget();
  }
}
