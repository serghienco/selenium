package com.slava.stp.command.$store;

import com.slava.stp.command.AbstractCommand;

public abstract class StoreAbstract extends AbstractCommand {

  public Object getStore(String key) {
    return driver.getStoreValue(key);
  }

  public Object removeStore(String key) {
    return driver.removeStoreValue(key);
  }

  public void putStore(String key, Object value) {
    driver.putStoreValue(key, value);
  }
}
