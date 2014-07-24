package com.slava.stp.util;

import java.net.URL;

public class ClassUtil {

  public static String getClassLocation(Class c) {
    return getClassLocation(c, c.getClassLoader());
  }

  public static String getClassLocation(Class c, ClassLoader loader) {
    final String classLocation = c.getName().replace('.', '/') + ".class";
    URL location = loader.getResource(classLocation);
    if (location == null) {
      location = ClassLoader.getSystemClassLoader().getResource(classLocation);
    }
    return location.toString();
  }

}
