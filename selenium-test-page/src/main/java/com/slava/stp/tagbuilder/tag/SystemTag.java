package com.slava.stp.tagbuilder.tag;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.slava.stp.tagbuilder.AbstractTag;
import com.slava.stp.tagbuilder.ParameterName;

public class SystemTag extends AbstractTag {

  private Map<String, String> map = new HashMap<String, String>();

  @Override
  public void init() {
    setParameter(ParameterName.SYSTEM_PROPERTY_MAP, map);
  }

  @Override
  public void doEndTag(Writer out) throws IOException {
    for (Entry<String, String> property : map.entrySet()) {
      System.setProperty(property.getKey(), property.getValue());
    }
  }
}
