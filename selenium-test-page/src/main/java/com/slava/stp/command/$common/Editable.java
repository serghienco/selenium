package com.slava.stp.command.$common;

import com.slava.stp.seleniumemulation.IsEditable;

public class Editable extends CommonAbstract {

  @Override
  public Object execute() {
    return new IsEditable(getElementFinder()).is(getDriver(), getTarget());
  }

  @Override
  public String getValueOrTargetHelp() {
    return getValue();
  }
}
