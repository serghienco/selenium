package com.slava.stp.tagbuilder.tag;

import java.io.IOException;
import java.io.Writer;

import com.slava.stp.tagbuilder.AbstractTag;
import com.slava.stp.util.StringUtil;

public class TestTag extends AbstractTag {

  private String absoluteName;
  private String className;
  private String packageName;

  @Override
  public void init() {
    absoluteName = getAttribute("name");
    String[] splitByLast = StringUtil.getSplitByLast(absoluteName, ".");
    if (splitByLast != null) {
      packageName = splitByLast[0];
      className = splitByLast[1];
    } else {
      packageName = null;
      className = absoluteName;
    }
  }

  private void includePackage(Writer out) throws IOException {
    if (packageName != null) {
      out.append("package ").append(packageName).append(";");
    }
  }

  private void includeImport(Writer out) throws IOException {
    out.append("import org.openqa.selenium.*;");
    out.append("import com.slava.stp.runnable.*;");
    out.append("import com.slava.stp.shell.*;");
    out.append("import com.slava.stp.util.*;");
  }

  @Override
  public void doBeginTag(Writer out) throws IOException {
    includePackage(out);
    includeImport(out);
    out.append("class ").append(className).append(" extends AbstractTest").append("{");
  }

  @Override
  public void doEndTag(Writer out) throws IOException {
    out.append("public static void main(String[]args){");
    out.append("def test=new ").append(className).append("();");
    out.append("test.run()}}");
  }
}
