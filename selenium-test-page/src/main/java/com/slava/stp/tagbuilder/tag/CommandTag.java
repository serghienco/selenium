package com.slava.stp.tagbuilder.tag;

import java.io.IOException;
import java.io.Writer;

import com.slava.stp.tagbuilder.AbstractTag;
import com.slava.stp.tagbuilder.ParameterName;
import com.slava.stp.util.StringUtil;

public class CommandTag extends AbstractTag {

  private String name;
  private String loop;

  @Override
  public void init() {
    name = getAttribute("name");
    loop = getAttribute("loop", null);
  }

  private String getGStringTarget() {
    return StringUtil.toGString(getParameter(ParameterName.TARGET));
  }

  private String getGStringValue() {
    return StringUtil.toGString(getParameter(ParameterName.VALUE));
  }

  private String getGStringName() {
    return StringUtil.toGString(name);
  }

  private String getDriver() {
    return (String) getParameter(ParameterName.DRIVER, null);
  }

  @Override
  public void doEndTag(Writer out) throws IOException {
    if (loop == null) {
      writeCommand(out, true);
    } else {
      writeCommandWithLoopCheck(out);
    }
  }

  private void writeCommandWithLoopCheck(Writer out) throws IOException {
    out.append("try{");
    writeCommand(out, false);
    out.append("}catch(AssertionError e){").append(loop).append("}catch(NoSuchElementException e){").append(loop).append("};");
  }

  private void writeCommand(Writer out, boolean successMessage) throws IOException {
    out.append(getDriver()).append(".d(").append(getGStringName()).append(",").append(getGStringTarget()).append(",").append(getGStringValue());
    if (!successMessage) {
      out.append(",false");
    }
    out.append(");");
  }
}
