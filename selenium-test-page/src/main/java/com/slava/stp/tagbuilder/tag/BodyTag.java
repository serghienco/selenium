package com.slava.stp.tagbuilder.tag;

import java.io.IOException;
import java.io.Writer;

import com.slava.stp.tagbuilder.AbstractTag;
import com.slava.stp.tagbuilder.ParameterName;
import com.slava.stp.util.StringUtil;

public class BodyTag extends AbstractTag {

  private String level;
  private String pattern;
  private String[] arguments;
  private String[] parameters;

  @Override
  public void init() throws Exception {
    level = getAttribute("level", "");
    pattern = getAttribute("pattern", "[%-5p] %m%n");
    arguments = getAttribute("arguments", "").split(",");
    parameters = getAttribute("parameters", "").split(",");
  }

  private void writeArguments(Writer out) throws IOException {
    for (int i = 0, len = arguments.length; i < len; i++) {
      String arg = arguments[i].trim();
      if (arg.length() > 0) {
        out.append("def ").append(arg).append("=").append("(arguments.length>" + i + ")?arguments[" + i + "]:null;");
      }
    }
  }

  private void setParameters() {
    for (int i = 0, len = parameters.length; i < len; i++) {
      String param = parameters[i].trim();
      if (param.length() > 0) {
        ParameterName paramName = ParameterName.valueOf(param.toUpperCase());
        String value = "arguments[" + i + "]";
        if (arguments.length > i) {
          String arg = arguments[i].trim();
          if (arg.length() > 0) {
            value = arg;
          }
        }
        setParameter(paramName, value);
      }
    }

  }

  private String getGStringLevel() {
    return StringUtil.toGString(level);
  }

  private String getGStringPattern() {
    return StringUtil.toGString(pattern);
  }

  @Override
  public void doBeginTag(Writer out) throws IOException {
    setParameters();
    out.append("Object run(Object ... arguments){try{");
    writeArguments(out);
    out.append("init(").append(getGStringLevel()).append(",").append(getGStringPattern()).append(",arguments);");
  }

  @Override
  public void doEndTag(Writer out) throws IOException {
    out.append("}catch(Throwable t){exception(t);}finally{destory();}};");
  }
}
