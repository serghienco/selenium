package com.slava.stp.command.$;

import com.slava.stp.command.AbstractCommand;

public class MouseOverMenuClickSubMeniu extends AbstractCommand {

  @Override
  public void exe() {
    getActions().moveToElement(getElementByLocatorTarget()).moveToElement(getElementByLocatorValue()).click().build().perform();
  }
}
