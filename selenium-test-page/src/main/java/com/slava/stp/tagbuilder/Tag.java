package com.slava.stp.tagbuilder;

import java.io.Writer;
import java.util.LinkedList;

import org.xml.sax.Attributes;

public interface Tag {

  void init() throws Exception;

  void doBeginChildElement(Writer out) throws Exception;

  void doEndChildElement(Writer out) throws Exception;

  void doBeginTag(Writer out) throws Exception;

  void doEndTag(Writer out) throws Exception;

  void doText(Writer out, String text) throws Exception;

  void setAttributes(Attributes attributes);

  void setStack(LinkedList<Tag> stack);

  void setName(String name);

  void setAbsoluteClassName(String absoluteClassName);
}
