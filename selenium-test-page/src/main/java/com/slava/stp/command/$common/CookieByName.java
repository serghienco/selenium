package com.slava.stp.command.$common;

import org.openqa.selenium.Cookie;

public class CookieByName extends CommonAbstract {

  @Override
  public Object execute() {
    Cookie cookie = getDriver().manage().getCookieNamed(getTarget());
    return cookie == null ? null : cookie.getValue();
  }

  @Override
  public String getValueOrTargetHelp() {
    return getValue();
  }
}
