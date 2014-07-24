package com.slava.stp.command.$common;

import com.slava.stp.seleniumemulation.IsOrdered;

public class Ordered extends CommonAbstract {

  @Override
  public Object execute() {
    return new IsOrdered(getElementFinder(), getJavascriptLibrary()).is(getDriver(), getTarget(), getValue());
  }

  @Override
  public String getValueOrTargetHelp() {
    return null;
  }
}
