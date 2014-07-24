package com.slava.stp.tagbuilder.tag;

import java.io.Writer;

import com.slava.stp.tagbuilder.AbstractTag;

public class IfTag extends AbstractTag {

  private String test;

  @Override
  public void init() throws Exception {
    test = getAttribute("test");
  }

  @Override
  public void doBeginTag(Writer out) throws Exception {
    out.append("if(").append(test).append("){");
  }

  @Override
  public void doEndTag(Writer out) throws Exception {
    out.append("};");
  }
}
