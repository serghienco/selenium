package com.slava.stp.command.$common;

public class Expression extends CommonAbstract {

  @Override
  public Object execute() {
    return getTarget();
  }

  @Override
  public String getValueOrTargetHelp() {
    return getValue();
  }
}
