package com.slava.stp.ui;

import java.util.List;
import java.util.regex.Pattern;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.slava.stp.util.StringUtil;

public class Select extends org.openqa.selenium.support.ui.Select {

  private final WebElement element;

  public Select(WebElement element) {
    super(element);
    this.element = element;
  }

  public void selectByIndex(String index) {
    super.selectByIndex(Integer.valueOf(index));
  }

  @Override
  public void selectByValue(String value) {
    super.selectByValue(value);
  }

  @Override
  public void selectByVisibleText(String text) {
    String[] methods = StringUtil.getSplitByFirst(text, ":");
    if (methods != null) {
      String type = methods[0];
      String value = methods[1];
      if ("regexp".equals(type)) {
        Pattern pattern = Pattern.compile(value);
        for (WebElement o : getOptions()) {
          if (pattern.matcher(o.getText()).matches()) {
            setSelected(o);
            if (!isMultiple()) {
              return;
            }
          }
        }
      }
    }
    super.selectByVisibleText(text);
  }

  public void selectById(String id) {
    for (WebElement o : getOptions()) {
      if (o.getAttribute("id").equals(id)) {
        setSelected(o);
        return;
      }
    }
    throw new NoSuchElementException("Cannot locate element with id: " + id);
  }

  public void selectByLocator(String generalLocator) {
    String[] methods = StringUtil.getSplitByFirst(generalLocator, "=");
    if (methods != null) {
      String type = methods[0];
      String locator = methods[1];
      if ("value".equals(type)) {
        selectByValue(locator);
      } else if ("id".equals(type)) {
        selectById(locator);
      } else if ("index".equals(type)) {
        selectByIndex(locator);
      } else if ("label".equals(type)) {
        selectByVisibleText(locator);
      } else {
        selectByDefault(generalLocator);
      }
    } else {
      selectByDefault(generalLocator);
    }
  }

  private void selectByDefault(String locator) {
    selectByVisibleText(locator);
  }

  private void setSelected(WebElement option) {
    if (!option.isSelected()) {
      option.click();
    }
  }

  public String getFirstSelectedOptionProperty(String name) {
    return getFirstSelectedOption().getAttribute(name);
  }

  public String[] getAllSelectedOptionsProperty(String name) {
    List<WebElement> selectedOptions = getAllSelectedOptions();
    String[] propertys = new String[selectedOptions.size()];
    for (int i = 0; i < propertys.length; i++) {
      propertys[i] = selectedOptions.get(i).getAttribute(name);
    }
    return propertys;
  }

  public String getFirstSelectedOptionText() {
    return getFirstSelectedOption().getText();
  }

  public String[] getAllSelectedOptionsText() {
    List<WebElement> selectedOptions = getAllSelectedOptions();
    String[] propertys = new String[selectedOptions.size()];
    for (int i = 0; i < propertys.length; i++) {
      propertys[i] = selectedOptions.get(i).getText();
    }
    return propertys;
  }

}
