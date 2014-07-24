package com.slava.stp.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class DataUtil {

  public static Map getMap(File parent, String file) throws IOException {
    File f = (parent == null) ? new File(file) : new File(parent, file);
    Properties properties = new Properties();
    FileInputStream propertiesFile;
    try {
      propertiesFile = new FileInputStream(f);
    } catch (FileNotFoundException e) {
      return null;
    }
    properties.load(propertiesFile);
    propertiesFile.close();
    return properties;
  }
}
