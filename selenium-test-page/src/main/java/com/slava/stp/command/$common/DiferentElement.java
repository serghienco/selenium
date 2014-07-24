package com.slava.stp.command.$common;

import org.openqa.selenium.WebElement;

public class DiferentElement extends CommonAbstract {

  @Override
  public Object execute() {
    WebElement el = getElementByLocatorTarget();
    Object object = driver.getStore().get(getValue());
    return el != object;
  }

  @Override
  public String getValueOrTargetHelp() {
    return null;
  }
}
