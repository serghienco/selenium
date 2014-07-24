package com.slava.stp.command.$common;

public class Confirmation extends CommonAbstract {

  @Override
  public Object execute() {
    return getAlertOverride().getNextConfirmation(getDriver());
  }

  @Override
  public String getValueOrTargetHelp() {
    return getTarget();
  }
}
