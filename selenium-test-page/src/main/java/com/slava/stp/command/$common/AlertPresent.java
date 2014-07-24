package com.slava.stp.command.$common;

public class AlertPresent extends CommonAbstract {

  @Override
  public Object execute() {
    return getAlertOverride().isAlertPresent(getDriver());
  }

  @Override
  public String getValueOrTargetHelp() {
    return getTarget();
  }
}
