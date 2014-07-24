package com.slava.stp.seleniumemulation;

import org.openqa.selenium.WebDriver;

import com.slava.stp.util.StringUtil;

public class GetAllButtons extends com.thoughtworks.selenium.webdriven.commands.GetAllButtons {

  public String getJoinIds(WebDriver driver) {
    return StringUtil.join(",", super.handleSeleneseCommand(driver, null, null));
  }
}
