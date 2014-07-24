package com.slava.stp.tagbuilder;

import java.io.Writer;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import org.xml.sax.Attributes;

import com.slava.stp.exception.RequiredAttributeTagException;

public abstract class AbstractTag implements Tag {

  private LinkedList<Tag> stack;
  private Attributes attributes;
  private Map<ParameterName, Object> parameters = new HashMap<ParameterName, Object>();
  private String name;
  private String absoluteClassName;

  @Override
  public void init() throws Exception {
  }

  @Override
  public void doBeginTag(Writer out) throws Exception {
  }

  @Override
  public void doEndTag(Writer out) throws Exception {
  }

  @Override
  public void doBeginChildElement(Writer out) throws Exception {
  }

  @Override
  public void doEndChildElement(Writer out) throws Exception {
  }

  @Override
  public void doText(Writer out, String text) throws Exception {
  }

  @Override
  public void setAbsoluteClassName(String absoluteClassName) {
    this.absoluteClassName = absoluteClassName;
  }

  @Override
  public void setAttributes(Attributes attributes) {
    this.attributes = attributes;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public void setStack(LinkedList<Tag> stack) {
    this.stack = stack;
  }

  public Object getParentParameter(ParameterName name) {
    return getParentTag().getParameter(name);
  }

  public void setParentParameter(ParameterName name, Object value) {
    getParentTag().setParameter(name, value);
  }

  public Object removeParentParameter(ParameterName name) {
    return getParentTag().removeParameter(name);
  }

  public Object getDocumentParameter(ParameterName name) {
    return getDocumentTag().getParameter(name);
  }

  public void setDocumentParameter(ParameterName name, Object value) {
    getDocumentTag().setParameter(name, value);
  }

  public Object removeDocumentParameter(ParameterName name) {
    return getDocumentTag().removeParameter(name);
  }

  public Object getParameter(ParameterName name) {
    return parameters.get(name);
  }

  public void setParameter(ParameterName name, Object value) {
    parameters.put(name, value);
  }

  public Object removeParameter(ParameterName name) {
    return parameters.remove(name);
  }

  public Object getParameter(ParameterName name, String scope) {
    Iterator<Tag> descendingIterator = stack.descendingIterator();
    while (descendingIterator.hasNext()) {
      AbstractTag tag = (AbstractTag) descendingIterator.next();
      Map<ParameterName, Object> params = tag.parameters;
      if (params.containsKey(name) && (scope == null || scope.equals(tag.name))) {
        return params.get(name);
      }
    }
    return null;
  }

  public void setParameter(ParameterName name, Object value, String scope) {
    Iterator<Tag> descendingIterator = stack.descendingIterator();
    while (descendingIterator.hasNext()) {
      AbstractTag tag = (AbstractTag) descendingIterator.next();
      Map<ParameterName, Object> params = tag.parameters;
      if (params.containsKey(name) && (scope == null || scope.equals(tag.name))) {
        params.put(name, value);
        return;
      }
    }
  }

  public Object removeParameter(ParameterName name, String scope) {
    Iterator<Tag> descendingIterator = stack.descendingIterator();
    while (descendingIterator.hasNext()) {
      AbstractTag tag = (AbstractTag) descendingIterator.next();
      Map<ParameterName, Object> params = tag.parameters;
      if (params.containsKey(name) && (scope == null || scope.equals(tag.name))) {
        return params.remove(name);
      }
    }
    return null;
  }

  public String getAttribute(String name) {
    return getAttribute(this, name);
  }

  public String getAttribute(String name, String defaultValue) {
    return getAttribute(this, name, defaultValue);
  }

  public String getParentAttribute(String name) {
    return getAttribute(getParentTag(), name);
  }

  public String getParentAttribute(String name, String defaultValue) {
    return getAttribute(getParentTag(), name, defaultValue);
  }

  public String getDocumentAttribute(String name) {
    return getAttribute(getDocumentTag(), name);
  }

  public String getDocumentAttribute(String name, String defaultValue) {
    return getAttribute(getDocumentTag(), name, defaultValue);
  }

  public String getAttribute(AbstractTag tag, String name) {
    String value = tag.attributes.getValue(name);
    if (value == null) {
      throw new RequiredAttributeTagException(name);
    } else {
      return value;
    }
  }

  public String getAttribute(AbstractTag tag, String name, String defaultValue) {
    String value = tag.attributes.getValue(name);
    if (value == null) {
      return defaultValue;
    } else {
      return value;
    }
  }

  public AbstractTag getDocumentTag() {
    return (AbstractTag) stack.getFirst();
  }

  public AbstractTag getParentTag() {
    Iterator<Tag> descendingIterator = stack.descendingIterator();
    descendingIterator.next();
    return (AbstractTag) descendingIterator.next();
  }

  public String format(String pattern, Object... args) {
    return MessageFormat.format(pattern, args);
  }

  public LinkedList<Tag> getStack() {
    return stack;
  }

  public String getAbsoluteClassName() {
    return absoluteClassName;
  }
}
