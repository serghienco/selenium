package com.slava.stp.command.$verify;

import com.slava.stp.log.LoggerUtil;

public class Default extends VerifyAbstract {

  @Override
  public void exe() {
    try {
      getAssertInstance().execute();
    } catch (AssertionError e) {
      LoggerUtil.getLogger().warn(getVerifyMessage(e.toString() + "\n" + takesScreenshot()));
    }
  }

  private String getVerifyMessage(String mess) {
    return "Verify:    |" + getCommand() + "|" + getTarget() + "|" + getValue() + "|" + getDriver().getClass().getSimpleName() + "|" + mess;
  }
}
