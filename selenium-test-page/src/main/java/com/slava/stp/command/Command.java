package com.slava.stp.command;

import com.slava.stp.Driver;

public interface Command {

  void setDriver(Driver driver);

  Object execute();
}
