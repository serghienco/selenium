package com.slava.stp.sax.helpers;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.slava.stp.sax.exception.DetectAttributeSaxException;

public class RootAttributeHandler extends DefaultHandler {

  private String value;

  private String name;

  public RootAttributeHandler(String name) {
    this.name = name;
  }

  @Override
  public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    value = attributes.getValue(name);
    throw new DetectAttributeSaxException();
  }

  public String getValue() {
    return value;
  }
}
