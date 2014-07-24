package com.slava.stp.command.$.common;

import org.openqa.selenium.WebElement;

import com.slava.stp.command.AbstractCommand;

public class Check extends AbstractCommand {

  private boolean check;

  public Check(boolean check) {
    this.check = check;
  }

  @Override
  public void exe() {
    WebElement el = getElementByLocatorTarget();
    String tagName = el.getTagName();
    if (tagName.equals("input")) {
      String type = el.getAttribute("type");
      if (type.equals("checkbox") || type.equals("radio")) {
        if (el.isSelected() != check) {
          el.click();
        }
        return;
      }
      throw new UnsupportedOperationException("Unable to determine if input element type is radio or checkbox. Type is: " + type);
    }
    throw new UnsupportedOperationException("Unable to determine if element is input. Tag name is: " + tagName);
  }
}
