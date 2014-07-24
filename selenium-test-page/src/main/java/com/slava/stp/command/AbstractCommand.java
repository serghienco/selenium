package com.slava.stp.command;

import java.util.List;

import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.thoughtworks.selenium.webdriven.commands.AlertOverride;
import com.thoughtworks.selenium.webdriven.CompoundMutator;
import com.thoughtworks.selenium.webdriven.ElementFinder;
import com.thoughtworks.selenium.webdriven.JavascriptLibrary;
import com.thoughtworks.selenium.webdriven.Windows;

import com.slava.stp.By;
import com.slava.stp.Driver;
import com.slava.stp.WebDriverBackedSelenium;
import com.slava.stp.command.$common.CommonAbstract;
import com.slava.stp.exception.ValueFieldMustBeEmpty;
import com.slava.stp.junit.Assert;
import com.slava.stp.ui.Select;
import com.slava.stp.util.StringUtil;

public abstract class AbstractCommand implements Command {

    static public class CommonCommandResponse {

        public enum ResponseTypes {
            BOOLEAN, STRING, OBJECT, NULL;

            public static ResponseTypes getType(Object o) {
                if (o == null) {
                    return ResponseTypes.NULL;
                } else if (o instanceof Boolean) {
                    return ResponseTypes.BOOLEAN;
                } else if (o instanceof String) {
                    return ResponseTypes.STRING;
                } else {
                    return ResponseTypes.OBJECT;
                }
            }
        }

        private Object executedValue;
        private String secondValue;
        private boolean positive;

        public CommonCommandResponse(Object executeValue, String secondValue, boolean positive) {
            this.executedValue = executeValue;
            this.secondValue = secondValue;
            this.positive = positive;
        }

        public boolean isPositive() {
            return positive;
        }

        public String getSecondValue() {
            return secondValue;
        }

        public Object getExecutedValue() {
            return executedValue;
        }

        public String getExecutedValueString() {
            return executedValue.toString();
        }

        public boolean getExecutedValueBoolean() {
            return Boolean.valueOf(getExecutedValueString()).booleanValue();
        }

        public ResponseTypes getType() {
            return ResponseTypes.getType(executedValue);
        }
    }

    protected Driver driver;

    @Override
    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public String getBaseUrl() {
        return driver.getBaseUrl();
    }

    public String getDriverType() {
        return driver.getDriverType();
    }

    public WebDriverBackedSelenium getSelenium() {
        return driver.getSelenium();
    }

    public WebDriver getDriver() {
        return driver.getDriver();
    }

    public Actions getActions() {
        return new Actions(getDriver());
    }

    public JavascriptExecutor getJavascriptExecutor() {
        return (JavascriptExecutor) driver.getDriver();
    }

    public String getCommand() {
        return driver.getCommand();
    }

    public Windows getWindows() {
        return driver.getWindows();
    }

    public Mouse getMouse() {
        return ((HasInputDevices) getDriver()).getMouse();
    }

    public Keyboard getKeyboard() {
        return ((HasInputDevices) getDriver()).getKeyboard();
    }

    public AlertOverride getAlertOverride() {
        return driver.getAlertOverride();
    }

    public JavascriptLibrary getJavascriptLibrary() {
        return driver.getJavascriptLibrary();
    }

    public String getSeleniumScript(String name) {
        return getJavascriptLibrary().getSeleniumScript(name);
    }

    public ElementFinder getElementFinder() {
        return driver.getElementFinder();
    }

    public CompoundMutator getScriptMutator() {
        return driver.getScriptMutator();
    }

    public String getTarget() {
        return driver.getTarget();
    }

    public String getValue() {
        return driver.getValue();
    }

    public String getCommandType() {
        return driver.getCommandType();
    }

    public String getCommandName() {
        return driver.getCommandName();
    }

    public String takesScreenshot() {
        return driver.takesScreenshot();
    }

    public WebElement getElementByLocator(String locator) {
        return By.locator(locator).findElement(getDriver());
    }

    public List<WebElement> getElementsByLocator(String locator) {
        return By.locator(locator).findElements(getDriver());
    }

    public WebElement getElementByLocatorTarget() {
        return getElementByLocator(getTarget());
    }

    public WebElement getElementByLocatorValue() {
        return getElementByLocator(getValue());
    }

    public List<WebElement> getElementsByLocatorTarget() {
        return getElementsByLocator(getTarget());
    }

    public Select getSelectElementByLocatorTarget() {
        return new Select(getElementByLocatorTarget());
    }

    public String getAttributeByTargetLocator() {
        String target = getTarget();
        int lastIndexOf = target.lastIndexOf("@");
        String elementLocator = target.substring(0, lastIndexOf);
        String attributeName = target.substring(lastIndexOf + 1);
        return getElementByLocator(elementLocator).getAttribute(attributeName);
    }

    public CommonAbstract getCommonInstance() {
        CommonAbstract newCommonInstance = CommandUtil.newCommonInstance(this, getCommandName());
        newCommonInstance.setDriver(driver);
        return newCommonInstance;
    }

    public CommonCommandResponse getCommonInstanceValue() {
        String commandName = getCommandName();
        boolean positive = true;

        int indexOf = commandName.indexOf("Not");
        if (indexOf > -1) {
            positive = false;
            commandName = commandName.substring(0, indexOf) + commandName.substring(indexOf + 3);
        }

        CommonAbstract newCommonInstance = CommandUtil.newCommonInstance(this, commandName);
        newCommonInstance.setDriver(driver);
        return new CommonCommandResponse(newCommonInstance.execute(), newCommonInstance.getValueOrTargetHelp(), positive);
    }

    public Command getAssertInstance() {
        Command assertInstance = CommandUtil.newAssertInstance(this, getCommandName());
        assertInstance.setDriver(driver);
        return assertInstance;
    }

    public void checkValueFieldIsEmpty() {
        if (!StringUtil.isEmpty(getValue())) {
            throw new ValueFieldMustBeEmpty(this);
        }
    }

    @Override
    public Object execute() {
        exe();
        return null;
    }

    public void exe() {
    }

    @Override
    public String toString() {
        return "command=" + getCommand() + ", target=" + getTarget() + ", value=" + getValue();
    }

    public static void assertEquals(String expected, String actual) {
        Assert.assertEquals(expected, actual);
    }

    public static void assertNotEquals(String expected, String actual) {
        Assert.assertNotEquals(expected, actual);
    }

    public static void assertEquals(Object expected, Object actual) {
        Assert.assertEquals((Object) expected.toString(), (Object) actual.toString());
    }

    public static void assertNotEquals(Object expected, Object actual) {
        Assert.assertNotEquals((Object) expected.toString(), (Object) actual.toString());
    }

    public static void assertTrue(Object b) {
        Assert.assertTrue(((Boolean) b).booleanValue());
    }

    public static void assertFalse(Object b) {
        Assert.assertFalse(((Boolean) b).booleanValue());
    }

    public static void fail(String message) {
        Assert.fail(message);
    }
}
