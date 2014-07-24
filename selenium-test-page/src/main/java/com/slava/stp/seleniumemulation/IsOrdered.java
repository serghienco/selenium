package com.slava.stp.seleniumemulation;

import org.openqa.selenium.WebDriver;
import com.thoughtworks.selenium.webdriven.ElementFinder;
import com.thoughtworks.selenium.webdriven.JavascriptLibrary;

public class IsOrdered extends com.thoughtworks.selenium.webdriven.commands.IsOrdered {

  public IsOrdered(ElementFinder finder, JavascriptLibrary js) {
    super(finder, js);
  }

  public Boolean is(WebDriver driver, String locator1, String locator2) {
    return super.handleSeleneseCommand(driver, locator1, locator2);
  }
}
