package com.slava.stp.command.$common;

public class ConfirmationPresent extends CommonAbstract {

  @Override
  public Object execute() {
    return getAlertOverride().isConfirmationPresent(getDriver());
  }

  @Override
  public String getValueOrTargetHelp() {
    return getTarget();
  }

}
