package com.slava.stp.command.$common;

import com.slava.stp.seleniumemulation.GetAllButtons;

public class AllButtons extends CommonAbstract {

  @Override
  public Object execute() {
    return new GetAllButtons().getJoinIds(getDriver());
  }

  @Override
  public String getValueOrTargetHelp() {
    return getTarget();
  }
}
