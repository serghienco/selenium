package com.slava.stp.command.$common;

import com.slava.stp.seleniumemulation.IsSomethingSelected;

public class SomethingSelected extends CommonAbstract {

  @Override
  public Object execute() {
    return new IsSomethingSelected(getJavascriptLibrary()).is(getDriver(), getTarget());
  }

  @Override
  public String getValueOrTargetHelp() {
    return getValue();
  }
}
