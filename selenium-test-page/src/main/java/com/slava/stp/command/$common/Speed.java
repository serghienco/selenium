package com.slava.stp.command.$common;

public class Speed extends CommonAbstract {

  @Override
  public Object execute() {
    return "0";
  }

  @Override
  public String getValueOrTargetHelp() {
    return getTarget();
  }
}
