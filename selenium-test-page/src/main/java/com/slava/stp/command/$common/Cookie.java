package com.slava.stp.command.$common;

public class Cookie extends CommonAbstract {

  @Override
  public Object execute() {
    StringBuilder builder = new StringBuilder();
    for (org.openqa.selenium.Cookie c : getDriver().manage().getCookies()) {
      builder.append(c.toString());
      builder.append("; ");
    }
    return builder.toString();
  }

  @Override
  public String getValueOrTargetHelp() {
    return getTarget();
  }
}
