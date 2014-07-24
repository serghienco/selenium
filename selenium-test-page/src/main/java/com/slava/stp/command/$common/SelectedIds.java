package com.slava.stp.command.$common;

import com.slava.stp.util.StringUtil;

public class SelectedIds extends CommonAbstract {

  @Override
  public Object execute() {
    return StringUtil.join(",", getSelectElementByLocatorTarget().getAllSelectedOptionsProperty("id"));
  }

  @Override
  public String getValueOrTargetHelp() {
    return getValue();
  }
}
