package com.slava.stp.tagbuilder.tag;

import java.io.Writer;

import com.slava.stp.tagbuilder.AbstractTag;
import com.slava.stp.util.StringUtil;

public class PropertiesMapTag extends AbstractTag {

  private String file;
  private boolean absolute;

  @Override
  public void init() throws Exception {
    file = getAttribute("file");
    absolute = Boolean.valueOf(getAttribute("absolute", "false"));
  }

  private String getGStringFile() {
    return StringUtil.toGString(file);
  }

  @Override
  public void doEndTag(Writer out) throws Exception {
    if (absolute) {
      out.append("DataUtil.getMap(null,");
    } else {
      out.append("DataUtil.getMap(testFolder,");
    }
    out.append(getGStringFile()).append(")");
  }
}
