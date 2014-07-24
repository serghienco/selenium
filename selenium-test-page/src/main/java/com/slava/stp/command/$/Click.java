package com.slava.stp.command.$;

import com.slava.stp.command.AbstractCommand;

public class Click extends AbstractCommand {

  @Override
  public void exe() {
    getElementByLocatorTarget().click();
  }
}
