package com.slava.stp.command.$common;

import com.slava.stp.seleniumemulation.GetElementIndex;

public class ElementIndex extends CommonAbstract {

  @Override
  public Object execute() {
    return new GetElementIndex(getElementFinder(), getJavascriptLibrary()).getIndex(getDriver(), getTarget());
  }

  @Override
  public String getValueOrTargetHelp() {
    return getValue();
  }
}
