package com.slava.stp.command.$common;

import com.slava.stp.exception.NotImplementedCommandException;

public class PromptPresent extends CommonAbstract {

  @Override
  public Object execute() {
    throw new NotImplementedCommandException(this);
  }

  @Override
  public String getValueOrTargetHelp() {
    throw new NotImplementedCommandException(this);
  }

}
