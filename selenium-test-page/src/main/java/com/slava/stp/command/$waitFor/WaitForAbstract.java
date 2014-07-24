package com.slava.stp.command.$waitFor;

import com.slava.stp.command.AbstractCommand;

public abstract class WaitForAbstract extends AbstractCommand {

  public long getSleepTime() {
    return driver.getSleepTime();
  }

  public long getTimeout() {
    return driver.getTimeout();
  }

  @Override
  public String toString() {
    return super.toString() + ", timeout=" + getTimeout() + "ms";
  }
}
