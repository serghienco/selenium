package com.slava.stp.sax;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import com.slava.stp.sax.exception.DetectAttributeSaxException;
import com.slava.stp.sax.helpers.RootAttributeHandler;
import com.slava.stp.sax.helpers.TagBuilderHandler;

public class XmlUtil {

  private static final SAXParserFactory factory = SAXParserFactory.newInstance();

  public static String getAttribute(File file, String attribute) throws ParserConfigurationException, SAXException, IOException {
    RootAttributeHandler handler = new RootAttributeHandler(attribute);
    try {
      factory.newSAXParser().parse(file, handler);
    } catch (DetectAttributeSaxException e) {
    }
    return handler.getValue();
  }

  public static String getClassName(File file) throws ParserConfigurationException, SAXException, IOException {
    return getAttribute(file, "name");
  }

  public static long getLastModified(File file) throws ParserConfigurationException, SAXException, IOException {
    try {
      return Long.valueOf(getAttribute(file, "lastModified"));
    } catch (NumberFormatException e) {
      return 0;
    }
  }

  public static void parseXmlToGroovy(long lastModified, File inXml, String absoluteClassName, File outXml, File outGroovy) throws SAXException, IOException, ParserConfigurationException {
    factory.newSAXParser().parse(inXml, new TagBuilderHandler(Long.toString(lastModified), absoluteClassName, outXml, outGroovy));
  }
}
