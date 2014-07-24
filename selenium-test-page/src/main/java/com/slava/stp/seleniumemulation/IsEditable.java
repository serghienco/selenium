package com.slava.stp.seleniumemulation;

import org.openqa.selenium.WebDriver;
import com.thoughtworks.selenium.webdriven.ElementFinder;

public class IsEditable extends com.thoughtworks.selenium.webdriven.commands.IsEditable {

  public IsEditable(ElementFinder finder) {
    super(finder);
  }

  public Boolean is(WebDriver driver, String locator) {
    return super.handleSeleneseCommand(driver, locator, null);
  }
}
