package com.slava.stp.command.$common;

public class HtmlSource extends CommonAbstract {

  @Override
  public Object execute() {
    return getDriver().getPageSource();
  }

  @Override
  public String getValueOrTargetHelp() {
    return getTarget();
  }
}
