package com.slava.stp.command.$common;

import com.slava.stp.util.StringUtil;

public class SelectedIndexes extends CommonAbstract {

  @Override
  public Object execute() {
    return StringUtil.join(",", getSelectElementByLocatorTarget().getAllSelectedOptionsProperty("index"));
  }

  @Override
  public String getValueOrTargetHelp() {
    return getValue();
  }
}
