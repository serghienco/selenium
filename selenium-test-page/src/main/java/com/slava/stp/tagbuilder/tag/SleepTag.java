package com.slava.stp.tagbuilder.tag;

import java.io.Writer;

import com.slava.stp.tagbuilder.AbstractTag;

public class SleepTag extends AbstractTag {

  private long time;

  @Override
  public void init() throws Exception {
    try {
      time = Long.valueOf(getAttribute("time", null));
    } catch (NumberFormatException e) {
      time = 1000;
    }
  }

  @Override
  public void doEndTag(Writer out) throws Exception {
    out.append("Thread.sleep(").append(Long.toString(time)).append(");");
  }
}
