package com.slava.stp.command.$store;

import com.slava.stp.Driver.CommandType;
import com.slava.stp.exception.NotImplementedCommandException;

public class Default extends StoreAbstract {

  @Override
  public void exe() {
    CommonCommandResponse r = getCommonInstanceValue();

    switch (r.getType()) {
      case BOOLEAN:
        if (r.isPositive()) {
          putStore(r.getSecondValue(), r.getExecutedValue());
        } else {
          putStore(r.getSecondValue(), !r.getExecutedValueBoolean());
        }
        break;
      default:
        if (r.isPositive()) {
          putStore(r.getSecondValue(), r.getExecutedValue());
        } else {
          throw new NotImplementedCommandException(this, CommandType.STORE_TYPE, getCommandName(), "null");
        }
        break;
    }
  }
}
