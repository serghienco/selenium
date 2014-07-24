package com.slava.stp.tagbuilder.tag;

import java.io.IOException;
import java.io.Writer;

import com.slava.stp.tagbuilder.AbstractTag;

public class DataTag extends AbstractTag {

  private String varName;
  private boolean def;

  @Override
  public void init() {
    varName = getAttribute("var");
    def = Boolean.valueOf(getAttribute("def", "true"));
  }

  @Override
  public void doBeginTag(Writer out) throws IOException {
    if (def) {
      out.append("def ");
    }
    out.append(varName).append("=");
  }

  @Override
  public void doText(Writer out, String text) throws IOException {
    out.append(text);
  }

  @Override
  public void doEndTag(Writer out) throws IOException {
    out.append(";");
  }
}
