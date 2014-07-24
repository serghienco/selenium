package com.slava.stp.tagbuilder.tag;

import java.io.IOException;
import java.io.Writer;

import com.slava.stp.tagbuilder.AbstractTag;

public class GroovyTag extends AbstractTag {

  private StringBuilder builder = new StringBuilder();

  @Override
  public void doText(Writer out, String text) throws IOException {
    builder.append(text);
  }

  @Override
  public void doEndTag(Writer out) throws Exception {
    out.append(builder.toString()).append("\n");
  }
}