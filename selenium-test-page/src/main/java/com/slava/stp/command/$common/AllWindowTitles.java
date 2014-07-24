package com.slava.stp.command.$common;

import org.openqa.selenium.WebDriver;

public class AllWindowTitles extends CommonAbstract {

  @Override
  public Object execute() {
    WebDriver driver = getDriver();
    String current = driver.getWindowHandle();

    String names = "";
    boolean first = true;
    for (String handle : driver.getWindowHandles()) {
      driver.switchTo().window(handle);
      if (first) {
        first = false;
      } else {
        names += ",";
      }
      names += driver.getTitle();
    }
    driver.switchTo().window(current);
    return names;
  }

  @Override
  public String getValueOrTargetHelp() {
    return getTarget();
  }
}
