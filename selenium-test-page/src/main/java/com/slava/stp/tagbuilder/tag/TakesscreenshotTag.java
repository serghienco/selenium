package com.slava.stp.tagbuilder.tag;

import com.slava.stp.tagbuilder.ParameterName;

public class TakesscreenshotTag extends LogTag {

  private String getDriver() {
    return (String) getParameter(ParameterName.DRIVER, null);
  }

  @Override
  protected String getLogMessage() {   
    return super.getLogMessage() + "${" + getDriver() + ".takesScreenshot()}";
  }
}
