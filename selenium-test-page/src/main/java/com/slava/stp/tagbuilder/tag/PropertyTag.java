package com.slava.stp.tagbuilder.tag;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import com.slava.stp.tagbuilder.ParameterName;

public class PropertyTag extends CommandTag {

  private StringBuilder b = new StringBuilder();
  private String name;

  @Override
  public void init() {
    name = getAttribute("name");
  }

  @Override
  public void doText(Writer out, String text) throws IOException {
    b.append(text);
  }

  @Override
  public void doEndTag(Writer out) throws IOException {
    ((Map<String, String>) getParentParameter(ParameterName.SYSTEM_PROPERTY_MAP)).put(name, b.toString());
  }
}
