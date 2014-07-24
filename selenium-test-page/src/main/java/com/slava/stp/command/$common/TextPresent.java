package com.slava.stp.command.$common;

import com.slava.stp.seleniumemulation.IsTextPresent;

public class TextPresent extends CommonAbstract {

  @Override
  public Object execute() {
    return new IsTextPresent(getJavascriptLibrary()).is(getDriver(), getTarget());
  }

  @Override
  public String getValueOrTargetHelp() {
    return getValue();
  }
}
