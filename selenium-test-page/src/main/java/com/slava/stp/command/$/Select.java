package com.slava.stp.command.$;

import com.slava.stp.command.AbstractCommand;

public class Select extends AbstractCommand {

  @Override
  public void exe() {
    getSelectElementByLocatorTarget().selectByLocator(getValue());
  }
}
