package com.slava.stp.command.$common;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class AttributeFromAllWindows extends CommonAbstract {

  @Override
  public Object execute() {
    String attributeName = getTarget();
    WebDriver driver = getDriver();
    String current = driver.getWindowHandle();

    String attributes = "";
    boolean first = true;
    for (String handle : driver.getWindowHandles()) {
      driver.switchTo().window(handle);
      if (first) {
        first = false;
      } else {
        attributes += ",";
      }
      attributes += ((JavascriptExecutor) driver).executeScript("return '' + window[arguments[0]];", attributeName);
    }
    driver.switchTo().window(current);
    return attributes;
  }

  @Override
  public String getValueOrTargetHelp() {
    return getValue();
  }
}
