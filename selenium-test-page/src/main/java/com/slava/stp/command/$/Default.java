package com.slava.stp.command.$;

import com.slava.stp.command.AbstractCommand;

public class Default extends AbstractCommand {

  @Override
  public void exe() {
    getSelenium().doCommand(getCommand(), getTarget(), getValue());
  }
}
