package com.slava.stp.command.$common;

import com.slava.stp.seleniumemulation.GetAllLinks;

public class AllLinks extends CommonAbstract {

  @Override
  public Object execute() {
    return new GetAllLinks().getJoinIds(getDriver());
  }

  @Override
  public String getValueOrTargetHelp() {
    return getTarget();
  }
}
