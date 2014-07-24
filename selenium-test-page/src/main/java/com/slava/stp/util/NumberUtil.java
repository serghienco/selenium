package com.slava.stp.util;

public class NumberUtil {

  public static long getLongSystemProperty(String key, long def) {
    try {
      return Long.parseLong(System.getProperty(key));
    } catch (Throwable e) {
      return def;
    }
  }
}
