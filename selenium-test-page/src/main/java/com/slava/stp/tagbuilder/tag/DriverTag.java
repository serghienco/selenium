package com.slava.stp.tagbuilder.tag;

import java.io.IOException;
import java.io.Writer;

import com.slava.stp.Driver;
import com.slava.stp.tagbuilder.ParameterName;
import com.slava.stp.util.StringUtil;

public class DriverTag extends CommandTag {

  private static final String LOCAL_DRIVER_CLASS = Driver.class.getCanonicalName();

  private String driverVarName;
  private String baseUrl;
  private String driverClassName;
  private String timeout;
  private String sleeptime;

  @Override
  public void init() {
    timeout = getAttribute("timeout", "30000");
    sleeptime = getAttribute("sleeptime", "1000");
    driverVarName = getAttribute("var", "driver");
    baseUrl = getAttribute("baseUrl");
    driverClassName = getAttribute("type", "firefox");
    setParameter(ParameterName.DRIVER, driverVarName);
  }

  private String getGStringBaseUrl() {
    return StringUtil.toGString(baseUrl);
  }

  private String getGStringDriverClassName() {
    return StringUtil.toGString(driverClassName);
  }

  @Override
  public void doBeginTag(Writer out) throws IOException {
    out.append("def ").append(driverVarName).append("=new ").append(LOCAL_DRIVER_CLASS).append("(").append(getGStringDriverClassName()).append(",").append(getGStringBaseUrl()).append(",").append(timeout).append(",").append(sleeptime).append(");");
    out.append("try{");
  }

  @Override
  public void doEndTag(Writer out) throws IOException {
    out.append("}finally{").append(driverVarName).append(".quit()};");
  }
}
