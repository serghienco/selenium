package com.slava.stp;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import com.slava.stp.exception.SeleniumException;

public abstract class By extends org.openqa.selenium.By {

  public static By dom(final String dom) {
    if (dom == null) {
      throw new IllegalArgumentException("Cannot find elements with a null dom.");
    }
    return new ByDom(dom);
  }

  public static By identifier(final String identifier) {
    if (identifier == null) {
      throw new IllegalArgumentException("Cannot find elements with a null identifier.");
    }
    return new ByIdentifier(identifier);
  }

  public static By locator(final String locator) {
    if (locator == null) {
      throw new IllegalArgumentException("Cannot find elements with a null locator.");
    }
    return new ByLocator(locator);
  }

  public static class ByDom extends By {

    private String dom;

    public ByDom(String dom) {
      this.dom = dom;
    }

    @Override
    public List<WebElement> findElements(SearchContext context) {
      Object response = executeScript(context);
      if (response == null) {
        return new ArrayList<WebElement>();
      }
      if (response instanceof WebElement) {
        ArrayList<WebElement> webElementList = new ArrayList<WebElement>();
        webElementList.add((WebElement) response);
        return webElementList;
      }
      if (response instanceof List) {
        return (List<WebElement>) response;
      }
      throw new SeleniumException("Unexpected response for " + this + ", response=" + response);
    }

    @Override
    public WebElement findElement(SearchContext context) {
      Object response = executeScript(context);
      if (response instanceof WebElement) {
        return (WebElement) response;
      }
      throw new NoSuchElementException(getNoSuchElementExceptionMessage());
    }

    private Object executeScript(SearchContext context) {
      return ((JavascriptExecutor) context).executeScript("return " + dom);
    }

    @Override
    public String toString() {
      return "By.dom: " + dom;
    }
  }

  public static class ByIdentifier extends By {

    private String identifier;

    public ByIdentifier(String identifier) {
      this.identifier = identifier;
    }

    @Override
    public List<WebElement> findElements(SearchContext context) {
      List<WebElement> findElements = By.id(identifier).findElements(context);
      if (findElements.size() == 0) {
        findElements = By.name(identifier).findElements(context);
      }
      return findElements;
    }

    @Override
    public WebElement findElement(SearchContext context) {
      try {
        return By.id(identifier).findElement(context);
      } catch (NoSuchElementException e) {
        try {
          return By.name(identifier).findElement(context);
        } catch (NoSuchElementException e2) {
          throw new NoSuchElementException(getNoSuchElementExceptionMessage(), e);
        }
      }
    }

    @Override
    public String toString() {
      return "By.identifier: " + identifier;
    }
  }

  public static class ByLocator extends By {

    private static final String IDENTIFIER = "identifier";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String XPATH = "xpath";
    private static final String LINK = "link";
    private static final String DOM = "dom";
    private static final String CSS = "css";

    private static final String XPATH_START_WITH = "//";
    private static final String DOM_START_WITH = "document";

    private static final String COMMAND_SPLITER = "=";

    private String locator;
    private org.openqa.selenium.By by;

    public ByLocator(String locator) {
      if (locator.startsWith(XPATH_START_WITH)) {
        locator = XPATH + COMMAND_SPLITER + locator;
      } else if (locator.startsWith(DOM_START_WITH)) {
        locator = DOM + COMMAND_SPLITER + locator;
      }
      this.locator = locator;

      int indexOf = locator.indexOf(COMMAND_SPLITER);
      if (indexOf != -1) {
        String type = locator.substring(0, indexOf).trim();
        String value = locator.substring(indexOf + 1);
        if (type.equals(IDENTIFIER)) {
          by = By.identifier(value);
        } else if (type.equals(ID)) {
          by = By.id(value);
        } else if (type.equals(NAME)) {
          by = By.name(value);
        } else if (type.equals(XPATH)) {
          by = By.xpath(value);
        } else if (type.equals(LINK)) {
          by = By.linkText(value);
        } else if (type.equals(DOM)) {
          by = By.dom(value);
        } else if (type.equals(CSS)) {
          by = By.cssSelector(value);
        } else {
          by = getDefaultTypeLocator(locator);
        }
      } else {
        by = getDefaultTypeLocator(locator);
      }
    }

    @Override
    public List<WebElement> findElements(SearchContext context) {
      return by.findElements(context);
    }

    @Override
    public WebElement findElement(SearchContext context) {
      try {
        return by.findElement(context);
      } catch (NoSuchElementException e) {
        throw new NoSuchElementException(getNoSuchElementExceptionMessage(), e);
      }
    }

    @Override
    public String toString() {
      return "By.locator: " + locator + " > " + by;
    }

    private By getDefaultTypeLocator(String value) {
      return By.identifier(value);
    }
  }

  protected String getNoSuchElementExceptionMessage() {
    return "Cannot locate an element using " + this;
  }
}
