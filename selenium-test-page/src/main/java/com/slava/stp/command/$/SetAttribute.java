package com.slava.stp.command.$;

import org.openqa.selenium.WebElement;

import com.slava.stp.command.AbstractCommand;

public class SetAttribute extends AbstractCommand {

  @Override
  public void exe() {
    String target = getTarget();
    int lastIndexOf = target.lastIndexOf("@");
    String elementLocator = target.substring(0, lastIndexOf);
    String attributeName = target.substring(lastIndexOf + 1);
    WebElement el = getElementByLocator(elementLocator);
    getJavascriptExecutor().executeScript("arguments[0].setAttribute(arguments[1],arguments[2]);", el, attributeName, getValue());    
  }
}
