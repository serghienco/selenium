package com.slava.stp.command.$common;

import com.slava.stp.seleniumemulation.GetAllFields;

public class AllFields extends CommonAbstract {

  @Override
  public Object execute() {
    return new GetAllFields().getJoinIds(getDriver());
  }

  @Override
  public String getValueOrTargetHelp() {
    return getTarget();
  }
}
