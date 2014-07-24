package com.slava.stp.tagbuilder.tag;

import java.io.IOException;
import java.io.Writer;

import com.slava.stp.tagbuilder.AbstractTag;
import com.slava.stp.tagbuilder.ParameterName;

public class ValueTag extends AbstractTag {

  StringBuilder b = new StringBuilder();

  @Override
  public void doText(Writer out, String text) throws IOException {
    b.append(text);
  }

  @Override
  public void doEndTag(Writer out) throws IOException {
    setParentParameter(ParameterName.VALUE, b.toString());
  }
}
