package com.slava.stp.shell;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.slava.stp.log.LoggerUtil;
import com.slava.stp.runnable.Test;
import com.slava.stp.tagbuilder.SeleniumTagBuilder;

import groovy.lang.GroovyClassLoader;

public class SeleniumShell {

  private static SeleniumShell seleniumShell;
  private final GroovyClassLoader groovyClassLoader = new GroovyClassLoader();

  private final HashMap<String, Test> objectTestCash = new HashMap<String, Test>();
  private SeleniumTagBuilder tagBuilder;

  private SeleniumShell() {
    solveChromeDriver();
    setClasspath();
    tagBuilder = new SeleniumTagBuilder();
    tagBuilder.setWorkDir("./resource/work");
    LoggerUtil.setWorkDir("./resource/log");
  }

  private void setClasspath() {
    groovyClassLoader.addClasspath("./resource/work");
  }

  private void solveChromeDriver() {
    new ResourceSolver("chromedriver.exe", "./resource/chromedriver").solve();
    System.setProperty("webdriver.chrome.driver", "./resource/chromedriver/chromedriver.exe");
  }

  public static SeleniumShell getInstance() {
    if (seleniumShell == null) {
      seleniumShell = new SeleniumShell();
    }
    return seleniumShell;
  }

  private Test createTest(String file) throws SAXException, IOException, ParserConfigurationException, InstantiationException, IllegalAccessException, ClassNotFoundException {
    tagBuilder.parse(file);
    if (tagBuilder.isParse()) {
      setClasspath();
    }
    return (Test) groovyClassLoader.loadClass(tagBuilder.getAbsoluteClassName()).newInstance();
  }

  private Test getTest(String file) throws SAXException, IOException, ParserConfigurationException, InstantiationException, IllegalAccessException, ClassNotFoundException {
    Test testObject = objectTestCash.get(file);
    if (testObject == null) {
      testObject = createTest(file);
      objectTestCash.put(file, testObject);
    }
    return testObject;
  }

  public void run(String file, Object... arguments) throws SAXException, IOException, ParserConfigurationException, InstantiationException, IllegalAccessException, ClassNotFoundException {
    Test test = getTest(file);
    test.setTestFolder(new File(file).getParentFile());
    test.run(arguments);
  }
}
