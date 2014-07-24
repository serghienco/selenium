package com.slava.stp.command.$common;

public class CookiePresent extends CommonAbstract {

  @Override
  public Object execute() {
    return getDriver().manage().getCookieNamed(getTarget()) != null;
  }

  @Override
  public String getValueOrTargetHelp() {
    return getValue();
  }
}
