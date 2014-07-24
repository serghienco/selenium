package com.slava.stp.command.$common;

import com.slava.stp.By;

public class BodyText extends CommonAbstract {

  @Override
  public Object execute() {
    return getDriver().findElement(By.tagName("BODY")).getText();
  }

  @Override
  public String getValueOrTargetHelp() {
    return getTarget();
  }
}
