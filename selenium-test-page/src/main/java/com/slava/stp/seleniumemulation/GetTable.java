package com.slava.stp.seleniumemulation;

import com.thoughtworks.selenium.webdriven.ElementFinder;
import org.openqa.selenium.WebDriver;
import com.thoughtworks.selenium.webdriven.JavascriptLibrary;

public class GetTable extends com.thoughtworks.selenium.webdriven.commands.GetTable {

    public GetTable(ElementFinder finder, JavascriptLibrary js) {
        super(finder, js);
    }

    public String getText(WebDriver driver, String tableCellAddress) {
        return super.handleSeleneseCommand(driver, tableCellAddress, null);
    }
}
