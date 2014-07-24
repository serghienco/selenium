package com.slava.stp;

import org.openqa.selenium.WebDriver;

import com.google.common.base.Supplier;

public class WebDriverBackedSelenium extends com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium {

    private String[] pack(String... args) {
        return args;
    }

    public WebDriverBackedSelenium(Supplier<WebDriver> maker, String baseUrl) {
        super(maker, baseUrl);
    }

    public WebDriverBackedSelenium(WebDriver baseDriver, String baseUrl) {
        super(baseDriver, baseUrl);
    }

    public String doCommand(String commandName, String target, String value) {
        return commandProcessor.doCommand(commandName, pack(target, value));
    }

    public String getString(String commandName, String target, String value) {
        return commandProcessor.getString(commandName, pack(target, value));
    }

    public String[] getStringArray(String commandName, String target, String value) {
        return commandProcessor.getStringArray(commandName, pack(target, value));
    }

    public Number getNumber(String commandName, String target, String value) {
        return commandProcessor.getNumber(commandName, pack(target, value));
    }

    public boolean getBoolean(String commandName, String target, String value) {
        return commandProcessor.getBoolean(commandName, pack(target, value));
    }
}
