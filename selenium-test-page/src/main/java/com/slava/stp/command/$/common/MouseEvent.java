package com.slava.stp.command.$.common;

import com.slava.stp.command.AbstractCommand;

public class MouseEvent extends AbstractCommand {

  public static final String MOUSE_OVER = "mouseover";
  public static final String MOUSE_OUT = "mouseout";
  public static final String MOUSE_MOVE = "mousemove";
  public static final String MOUSE_DOWN = "mousedown";
  public static final String MOUSE_UP = "mouseup";

  private String script;
  private String type;

  public MouseEvent(String type, String script) {
    this.type = type;
    this.script = script;
  }

  public MouseEvent(String type) {
    this(type, "fireEvent.js");
  }

  @Override
  public void exe() {
    getJavascriptExecutor().executeScript(getScript(), new Object[] {
        getElementByLocatorTarget(), type, getValue()
    });
  }

  private String getScript() {
    return "return (" + getJavascriptLibrary().getSeleniumScript(script) + ").apply(null, arguments);";
  }
}
