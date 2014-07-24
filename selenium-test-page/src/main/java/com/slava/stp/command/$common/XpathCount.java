package com.slava.stp.command.$common;

public class XpathCount extends CommonAbstract {

  @Override
  public Object execute() {
    return getElementsByLocatorTarget().size();
  }

  @Override
  public String getValueOrTargetHelp() {
    return getValue();
  }
}
