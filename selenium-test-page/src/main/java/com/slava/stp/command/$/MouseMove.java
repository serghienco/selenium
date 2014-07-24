package com.slava.stp.command.$;

import com.slava.stp.command.AbstractCommand;

public class MouseMove extends AbstractCommand {

  @Override
  public void exe() {
    getActions().moveToElement(getElementByLocatorTarget()).build().perform();
  }
}
