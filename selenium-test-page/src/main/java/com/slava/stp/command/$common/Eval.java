package com.slava.stp.command.$common;

import org.openqa.selenium.JavascriptExecutor;

public class Eval extends CommonAbstract {

  @Override
  public Object execute() {
    StringBuilder builder = new StringBuilder();
    getScriptMutator().mutate(getTarget(), builder);
    Object result = ((JavascriptExecutor) getDriver()).executeScript(builder.toString());
    return result == null ? "" : result.toString();
  }

  @Override
  public String getValueOrTargetHelp() {
    return getValue();
  }

}
