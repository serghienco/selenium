package com.slava.stp.tagbuilder.tag;

import java.io.Writer;

import com.slava.stp.tagbuilder.AbstractTag;
import com.slava.stp.util.StringUtil;

public class IncludeTag extends AbstractTag {

  private String file;
  private String absolute;
  private String required;
  private String arguments;

  @Override
  public void init() throws Exception {
    file = getAttribute("file");
    absolute = Boolean.valueOf(getAttribute("absolute", "false")).toString();
    required = Boolean.valueOf(getAttribute("required", "true")).toString();
    arguments = getAttribute("arguments", null);
  }

  private String getGroovyFile() {
    return StringUtil.toGString(file);
  }

  public void doEndTag(Writer out) throws Exception {
    out.append("runSubTest(").append(getGroovyFile()).append(",").append(absolute).append(",").append(required);
    if (arguments != null) {
      out.append(",").append(arguments);
    }
    out.append(");");
  };
}
