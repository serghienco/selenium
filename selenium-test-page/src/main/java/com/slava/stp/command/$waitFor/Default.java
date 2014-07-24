package com.slava.stp.command.$waitFor;

import com.slava.stp.exception.SeleniumException;

public class Default extends WaitForAbstract {

  @Override
  public void exe() {
    long sleepTime = getSleepTime();
    long endTimeMillis = System.currentTimeMillis() + getTimeout();
    do {
      long startCicle = System.currentTimeMillis();
      try {
        getAssertInstance().execute();
        break;
      } catch (AssertionError e) {
        if (System.currentTimeMillis() > endTimeMillis) {
          fail("timeout " + this);
        }
        try {
          long cicleTime = System.currentTimeMillis() - startCicle;
          if (cicleTime < sleepTime) {
            Thread.sleep(sleepTime - cicleTime);
          }
        } catch (InterruptedException e1) {
          throw new SeleniumException(e1.getMessage(), e1);
        }
      }
    } while (true);
  }
}
