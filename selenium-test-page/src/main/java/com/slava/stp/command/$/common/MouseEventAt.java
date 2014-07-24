package com.slava.stp.command.$.common;

public class MouseEventAt extends MouseEvent {

  public static final String MOUSE_DOWN_AT = "mousedown";  
  public static final String MOUSE_UP_AT = "mouseup";
  public static final String MOUSE_MOVE_AT = "mousemove";

  public MouseEventAt(String type) {
    super(type, "fireEventAt.js");
  }
}
