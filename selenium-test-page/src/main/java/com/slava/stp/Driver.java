package com.slava.stp;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.thoughtworks.selenium.webdriven.CompoundMutator;
import com.thoughtworks.selenium.webdriven.ElementFinder;
import com.thoughtworks.selenium.webdriven.JavascriptLibrary;
import com.thoughtworks.selenium.webdriven.Windows;
import com.thoughtworks.selenium.webdriven.commands.AlertOverride;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.slava.stp.command.Command;
import com.slava.stp.command.CommandUtil;
import com.slava.stp.exception.DriverTypeException;
import com.slava.stp.log.LoggerUtil;
import com.slava.stp.util.FileUtil;
import com.slava.stp.util.StringUtil;

public class Driver {

    private static final Map<String, String> classesDriver = new HashMap<String, String>();

    static {
        classesDriver.put("firefox", FirefoxDriver.class.getCanonicalName());
        classesDriver.put("ie", InternetExplorerDriver.class.getCanonicalName());
        classesDriver.put("chrome", ChromeDriver.class.getCanonicalName());
    }

    Windows windows;
    AlertOverride alertOverride;
    JavascriptLibrary javascriptLibrary;
    ElementFinder elementFinder;
    CompoundMutator scriptMutator;
    Map<String, Object> store = new HashMap<String, Object>();

    public enum CommandType {
        ASSERT_TYPE("assert"), WAITFOR_TYPE("waitFor"), VERIFY_TYPE("verify"), STORE_TYPE("store"), COMMON_TYPE("common"), EMPTY_TYPE("");

        private String type;

        private CommandType(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return type;
        }

        public String getCommandName(String command) {
            return StringUtil.removeBegin(command, type);
        }

        public boolean is(String command) {
            return command.startsWith(type);
        }

        public static CommandType getCommandType(String command) {
            for (CommandType type : values()) {
                if (type.is(command)) {
                    return type;
                }
            }
            return null;
        }
    }

    private WebDriver driver;
    private WebDriverBackedSelenium selenium;
    private String baseUrl;

    private String commandName;
    private String commandType;

    private String command;
    private String target;
    private String value;
    private String driverType;

    private long timeout;
    private long sleepTime;

    public Driver(String driverType, String url, long timeout, long sleeptime) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        this.baseUrl = url;
        this.timeout = timeout;
        this.sleepTime = sleeptime;
        this.driverType = driverType;

        String driverClassName = classesDriver.get(driverType.toLowerCase());
        if (driverClassName == null) {
            throw new DriverTypeException(driverType);
        }
        driver = (WebDriver) Class.forName(driverClassName).newInstance();
        selenium = new WebDriverBackedSelenium(driver, baseUrl);

        windows = new Windows(driver);
        alertOverride = new AlertOverride(true);
        javascriptLibrary = new JavascriptLibrary();
        elementFinder = new ElementFinder(javascriptLibrary);
        scriptMutator = new CompoundMutator(baseUrl);

        Timeouts timeouts = driver.manage().timeouts();

        timeouts.implicitlyWait(5, TimeUnit.SECONDS);
    }

    public void d(String command, String target, String value) {
        d(command, target, value, true);
    }

    public void d(String command, String target, String value, boolean successMessage) {
        this.command = command;
        this.target = target;
        this.value = value;
        CommandType type = CommandType.getCommandType(command);
        commandType = type.toString();
        commandName = type.getCommandName(command);
        Command commandInstance = CommandUtil.newInstance(null, type, commandName);
        commandInstance.setDriver(this);

        Logger logger = LoggerUtil.getLogger();
        if (logger.isInfoEnabled()) {
            logger.info(getExecutingMessage(null));
        }

        boolean succesCommand = false;
        try {
            commandInstance.execute();
            succesCommand = true;
        } finally {
            if (successMessage && !succesCommand) {
                logger.warn(getExecutingMessage(takesScreenshot()));
            }
        }
    }

    private String getExecutingMessage(String message) {
        return "Executing: |" + command + "|" + target + "|" + value + "|" + driver.getClass().getSimpleName() + ((message != null) ? "\n" + message : "");
    }

    public void quit() {
        driver.quit();
    }

    public long getTimeout() {
        return timeout;
    }

    public long getSleepTime() {
        return sleepTime;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebDriverBackedSelenium getSelenium() {
        return selenium;
    }

    public String getCommand() {
        return command;
    }

    public String getTarget() {
        return target;
    }

    public String getValue() {
        return value;
    }

    public String getDriverType() {
        return driverType;
    }

    public String takesScreenshot() {
        String rootImagesPath = System.getProperty("test.workspace", ".../");
        String path = FileUtil.getImageName("images/", ".png");
        byte[] screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
        try {
            FileUtils.copyInputStreamToFile(new ByteArrayInputStream(screenshot), new File(path));
            return new StringBuilder("ScreenShot: ").append(rootImagesPath).append(path).toString();
        } catch (IOException e) {
            return new StringBuilder("Cant create a screenshot: ").append(rootImagesPath).append(path).append(", error: ").append(e).toString();
        }
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getCommandName() {
        return commandName;
    }

    public String getCommandType() {
        return commandType;
    }

    public void putStoreValue(String key, Object value) {
        store.put(key, value);
    }

    public Object getStoreValue(String key) {
        return store.get(key);
    }

    public Map<String, Object> getStore() {
        return store;
    }

    public Object removeStoreValue(String key) {
        return store.remove(key);
    }

    public Windows getWindows() {
        return windows;
    }

    public AlertOverride getAlertOverride() {
        return alertOverride;
    }

    public JavascriptLibrary getJavascriptLibrary() {
        return javascriptLibrary;
    }

    public ElementFinder getElementFinder() {
        return elementFinder;
    }

    public CompoundMutator getScriptMutator() {
        return scriptMutator;
    }
}
