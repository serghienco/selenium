package com.slava.stp.command.$common;

import com.slava.stp.util.StringUtil;

public class SelectedLabels extends CommonAbstract {

  @Override
  public Object execute() {

    return StringUtil.join(",", getSelectElementByLocatorTarget().getAllSelectedOptionsProperty("text"));
  }

  @Override
  public String getValueOrTargetHelp() {
    return getValue();
  }
}
