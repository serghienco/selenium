package com.slava.stp.shell;

import com.slava.stp.log.LoggerUtil;
import com.slava.stp.runnable.TestPage;
import com.slava.stp.tagbuilder.SeleniumTagBuilder;
import groovy.lang.GroovyClassLoader;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SeleniumShell {

    private static SeleniumShell seleniumShell;

    private final Map<String, TestPage> testPageCash = new HashMap<String, TestPage>();
    private final GroovyClassLoader groovyClassLoader = new GroovyClassLoader();

    private SeleniumTagBuilder tagBuilder;

    private SeleniumShell() {
        solveChromeDriver();
        setClasspath();
        tagBuilder = new SeleniumTagBuilder();
        tagBuilder.setWorkDir("./resource/work");
        LoggerUtil.setWorkDir("./resource/log");
    }

    public static SeleniumShell getInstance() {
        if (seleniumShell == null) {
            seleniumShell = new SeleniumShell();
        }
        return seleniumShell;
    }

    private void setClasspath() {
        groovyClassLoader.addClasspath("./resource/work");
    }

    private void solveChromeDriver() {
        new ResourceSolver("chromedriver.exe", "./resource/chromedriver").solve();
        System.setProperty("webdriver.chrome.driver", "./resource/chromedriver/chromedriver.exe");
    }

    private TestPage createTestPage(String file) throws SAXException, IOException, ParserConfigurationException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        tagBuilder.parse(file);
        if (tagBuilder.isParse()) {
            setClasspath();
        }
        return (TestPage) groovyClassLoader.loadClass(tagBuilder.getAbsoluteClassName()).newInstance();
    }

    private TestPage getTestPage(String file) throws SAXException, IOException, ParserConfigurationException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        TestPage testPage = testPageCash.get(file);
        if (testPage == null) {
            testPage = createTestPage(file);
            testPageCash.put(file, testPage);
        }
        return testPage;
    }

    public void run(String file, Object... arguments) throws SAXException, IOException, ParserConfigurationException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        TestPage testPage = getTestPage(file);
        testPage.setTestPageFolder(new File(file).getParentFile());
        testPage.run(arguments);
    }
}
