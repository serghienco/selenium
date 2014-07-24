package com.slava.stp.command.$common;

import com.slava.stp.util.StringUtil;

public class SelectOptions extends CommonAbstract {

  @Override
  public Object execute() {
    return StringUtil.join(",", getSelectElementByLocatorTarget().getAllSelectedOptionsText());
  }

  @Override
  public String getValueOrTargetHelp() {
    return getValue();
  }
}