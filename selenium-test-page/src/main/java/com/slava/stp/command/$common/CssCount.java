package com.slava.stp.command.$common;

import org.openqa.selenium.By;

public class CssCount extends CommonAbstract {

  @Override
  public Object execute() {
    return getDriver().findElements(By.cssSelector(getTarget())).size();
  }

  @Override
  public String getValueOrTargetHelp() {
    return getValue();
  }
}
