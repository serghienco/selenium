package com.slava.stp.command.$;

import org.openqa.selenium.internal.Locatable;

import com.slava.stp.command.$.common.MouseEvent;

public class MouseOver extends MouseEvent {

  public MouseOver() {
    super(MOUSE_OVER);
  }

  @Override
  public void exe() {
    String driverType = getDriverType();
    if ("firefox".equals(driverType)) {
      firefoxMouseOver();
    }
    if ("ie".equals(driverType)) {
      ieMouseOver();
    } else {
      super.exe();
    }
  }

  private void ieMouseOver() {
    getMouse().mouseMove(((Locatable) getElementByLocatorTarget()).getCoordinates());
  }

  private void firefoxMouseOver() {
    getActions().moveToElement(getElementByLocatorTarget()).build().perform();
  }
}
