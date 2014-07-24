package com.slava.stp.command.$common;

import com.slava.stp.exception.NotImplementedCommandException;

@Deprecated
public class MouseSpeed extends CommonAbstract {

  @Override
  public Object execute() {
    throw new NotImplementedCommandException(this);
  }

  public String getValueOrTargetHelp() {
    throw new NotImplementedCommandException(this);
  }

}
