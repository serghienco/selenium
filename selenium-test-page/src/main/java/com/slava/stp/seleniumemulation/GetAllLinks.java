package com.slava.stp.seleniumemulation;

import org.openqa.selenium.WebDriver;

import com.slava.stp.util.StringUtil;

public class GetAllLinks extends com.thoughtworks.selenium.webdriven.commands.GetAllLinks {

  public String getJoinIds(WebDriver driver) {
    return StringUtil.join(",", super.handleSeleneseCommand(driver, null, null));
  }
}
