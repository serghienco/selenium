package com.slava.stp.util;

public class BooleanUtil {

  public static boolean valueOf(Object o) {
    if (o == null) {
      return false;
    }
    if (o instanceof Boolean) {
      return ((Boolean) o).booleanValue();
    }
    return Boolean.valueOf(o.toString());
  }
}
