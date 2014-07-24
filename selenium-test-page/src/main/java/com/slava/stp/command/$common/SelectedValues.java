package com.slava.stp.command.$common;

import com.slava.stp.util.StringUtil;

public class SelectedValues extends CommonAbstract {

  @Override
  public Object execute() {
    return StringUtil.join(",", getSelectElementByLocatorTarget().getAllSelectedOptionsProperty("value"));
  }

  @Override
  public String getValueOrTargetHelp() {
    return getValue();
  }
}
