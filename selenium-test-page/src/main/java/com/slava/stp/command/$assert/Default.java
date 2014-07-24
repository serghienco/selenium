package com.slava.stp.command.$assert;

import com.slava.stp.Driver.CommandType;
import com.slava.stp.exception.NotImplementedCommandException;

public class Default extends AssertAbstract {

  @Override
  public void exe() {
    CommonCommandResponse r = getCommonInstanceValue();
    switch (r.getType()) {
      case BOOLEAN:
        if (r.isPositive()) {
          assertTrue(r.getExecutedValueBoolean());
        } else {
          assertFalse(r.getExecutedValueBoolean());
        }
        break;
      case STRING:
        if (r.isPositive()) {
          assertEquals(r.getSecondValue(), r.getExecutedValueString());
        } else {
          assertNotEquals(r.getSecondValue(), r.getExecutedValueString());
        }
        break;
      case OBJECT:
        if (r.isPositive()) {
          assertEquals(r.getSecondValue(), r.getExecutedValue());
        } else {
          assertNotEquals(r.getSecondValue(), r.getExecutedValue());
        }
        break;
      default:
        throw new NotImplementedCommandException(this, CommandType.ASSERT_TYPE, getCommandName(), this.getClass().getCanonicalName());
    }
  }
}
