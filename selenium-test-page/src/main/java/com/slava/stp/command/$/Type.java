package com.slava.stp.command.$;

import org.openqa.selenium.WebElement;

import com.slava.stp.command.AbstractCommand;

public class Type extends AbstractCommand {

  @Override
  public void exe() {
    WebElement el = getElementByLocatorTarget();
    el.clear();
    el.sendKeys(getValue());
  }
}
