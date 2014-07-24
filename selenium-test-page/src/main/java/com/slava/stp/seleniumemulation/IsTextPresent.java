package com.slava.stp.seleniumemulation;

import org.openqa.selenium.WebDriver;
import com.thoughtworks.selenium.webdriven.JavascriptLibrary;

public class IsTextPresent extends com.thoughtworks.selenium.webdriven.commands.IsTextPresent {

    public IsTextPresent(JavascriptLibrary js) {
        super(js);
    }

    public Boolean is(WebDriver driver, String pattern) {
        return super.handleSeleneseCommand(driver, pattern, null);
    }

}
