package com.slava.stp.command.$common;

import com.slava.stp.seleniumemulation.GetTable;

public class Table extends CommonAbstract {

  @Override
  public Object execute() {
    return new GetTable(getElementFinder(), getJavascriptLibrary()).getText(getDriver(), getTarget());
  }

  @Override
  public String getValueOrTargetHelp() {
    return getValue();
  }
}
