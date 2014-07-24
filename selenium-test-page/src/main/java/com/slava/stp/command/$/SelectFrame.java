package com.slava.stp.command.$;

import com.slava.stp.command.AbstractCommand;

public class SelectFrame extends AbstractCommand {

  @Override
  public void exe() {
    getWindows().selectFrame(getDriver(), getTarget());
  }
}
