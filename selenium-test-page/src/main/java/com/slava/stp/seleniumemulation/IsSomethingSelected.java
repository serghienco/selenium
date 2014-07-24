package com.slava.stp.seleniumemulation;

import org.openqa.selenium.WebDriver;
import com.thoughtworks.selenium.webdriven.JavascriptLibrary;

public class IsSomethingSelected extends com.thoughtworks.selenium.webdriven.commands.IsSomethingSelected {

  public IsSomethingSelected(JavascriptLibrary library) {
    super(library);
  }

  public Boolean is(WebDriver driver, String selectLocator) {
    return super.handleSeleneseCommand(driver, selectLocator, null);
  }

}
