package com.slava.stp.command.$common;

public class Attribute extends CommonAbstract {

  @Override
  public Object execute() {
    return getAttributeByTargetLocator();
  }

  @Override
  public String getValueOrTargetHelp() {
    return getValue();
  }
}
