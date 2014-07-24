package com.slava.stp.tagbuilder.tag;

import java.io.IOException;
import java.io.Writer;

public class ForTag extends CommandTag {

  private String varName;
  private String code;

  @Override
  public void init() {
    varName = getAttribute("var");
    code = getAttribute("code");
  }

  @Override
  public void doBeginTag(Writer out) throws IOException {
    out.append("for(def ").append(varName).append(":").append(code).append("){");
  }

  @Override
  public void doEndTag(Writer out) throws IOException {
    out.append("};");
  }
}
