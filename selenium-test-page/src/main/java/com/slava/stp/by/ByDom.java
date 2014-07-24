package com.slava.stp.by;

import com.slava.stp.exception.SeleniumException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ByDom extends By {

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