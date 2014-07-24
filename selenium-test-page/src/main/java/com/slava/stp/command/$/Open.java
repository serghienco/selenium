package com.slava.stp.command.$;

import com.slava.stp.command.AbstractCommand;
import com.slava.stp.util.StringUtil;

public class Open extends AbstractCommand {

  @Override
  public String getTarget() {
    String target = super.getTarget();
    return (target.indexOf("://") == -1) ? StringUtil.concatByString(getBaseUrl(), target, "/") : target;
  }

  @Override
  public void exe() {
    getDriver().get(getTarget());
  }
}
