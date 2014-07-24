package com.slava.stp.tagbuilder.tag;

import java.io.Writer;

import com.slava.stp.tagbuilder.AbstractTag;
import com.slava.stp.util.StringUtil;

public class LogTag extends AbstractTag {

  private StringBuilder builder = new StringBuilder();

  private String level;

  @Override
  public void init() throws Exception {
    level = getAttribute("level", null);
  }

  protected String getLogMessage() {
    return builder.toString();
  }

  private String getGStringLogMessage() {
    return StringUtil.toGString(getLogMessage());
  }

  private String getGStringLevel() {
    return StringUtil.toGString(level);
  }

  @Override
  public void doText(Writer out, String text) throws Exception {
    builder.append(text);
  }

  @Override
  public void doEndTag(Writer out) throws Exception {
    out.append("log(").append(getGStringLevel()).append(",").append(getGStringLogMessage()).append(");");
  }
}
