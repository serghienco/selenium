package com.slava.stp.seleniumemulation;

import org.openqa.selenium.WebDriver;
import com.thoughtworks.selenium.webdriven.ElementFinder;
import com.thoughtworks.selenium.webdriven.JavascriptLibrary;

public class GetElementIndex extends com.thoughtworks.selenium.webdriven.commands.GetElementIndex {

  public GetElementIndex(ElementFinder finder, JavascriptLibrary js) {
    super(finder, js);
  }

  public Number getIndex(WebDriver driver, String locator) {
    return super.handleSeleneseCommand(driver, locator, null);
  }

}
