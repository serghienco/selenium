package com.slava.stp.tagbuilder;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.codehaus.groovy.tools.FileSystemCompiler;
import org.xml.sax.SAXException;

import com.slava.stp.log.LoggerUtil;
import com.slava.stp.sax.XmlUtil;
import com.slava.stp.util.FileUtil;
import com.slava.stp.util.StringUtil;

public class SeleniumTagBuilder {

  private long lastModified;
  private File locationFolder;
  private File outXmlFile;
  private File outGroovyFile;
  private File outClassFile;
  private File inXmlFile;
  private String absoluteClassName;
  private String className;
  private String packageName;
  private String workDir = ".";
  private boolean parse;

  public void setWorkDir(String workDir) {
    this.workDir = workDir;
  }

  private String getFileClassName() throws ParserConfigurationException, SAXException, IOException {
    return XmlUtil.getClassName(inXmlFile);
  }

  public long getLastModifiedFromOutXML() throws ParserConfigurationException, SAXException, IOException {
    return XmlUtil.getLastModified(outXmlFile);
  }

  public String getAbsoluteClassName() {
    return absoluteClassName;
  }

  public String getClassName() {
    return className;
  }

  public String getPackageName() {
    return packageName;
  }

  public File getOutGroovyFile() {
    return outGroovyFile;
  }

  public File getOutXmlFile() {
    return outXmlFile;
  }

  public File getOutClassFile() {
    return outClassFile;
  }

  private void solveFiles() throws IOException, ParserConfigurationException, SAXException {
    absoluteClassName = getFileClassName();
    String[] splitByLast = StringUtil.getSplitByLast(absoluteClassName, ".");
    if (splitByLast != null) {
      packageName = splitByLast[0];
      className = splitByLast[1];
    } else {
      packageName = null;
      className = absoluteClassName;
    }
    locationFolder = FileUtil.getFolderByRootPackage(workDir, packageName);
    outGroovyFile = FileUtil.getFile(locationFolder, className, "groovy");
    outXmlFile = FileUtil.getFile(locationFolder, className, "xml");
    outClassFile = FileUtil.getFile(locationFolder, className, "class");
    lastModified = inXmlFile.lastModified();
  }

  public boolean isParse() {
    return parse;
  }

  private boolean checkIsParse() {
    if (!outGroovyFile.isFile() || !outXmlFile.isFile() || !outClassFile.isFile()) {
      return true;
    }
    try {
      if (lastModified != getLastModifiedFromOutXML()) {
        return true;
      }
    } catch (ParserConfigurationException e) {
      return true;
    } catch (SAXException e) {
      return true;
    } catch (IOException e) {
      return true;
    }
    return false;
  }

  private void parseFiles() throws SAXException, IOException, ParserConfigurationException {
    XmlUtil.parseXmlToGroovy(lastModified, inXmlFile, absoluteClassName, outXmlFile, outGroovyFile);
    FileSystemCompiler.main(new String[] {
        "-d", workDir, outGroovyFile.getAbsolutePath()
    });

  }

  public void parse(String file) throws IOException, ParserConfigurationException, SAXException {
    try {
      inXmlFile = new File(file);
      solveFiles();
      parse = checkIsParse();
      if (parse) {
        parseFiles();
      }
    } catch (IOException e) {
      logError(e);
      throw e;
    } catch (ParserConfigurationException e) {
      logError(e);
      throw e;
    } catch (SAXException e) {
      logError(e);
      throw e;
    }
  }

  private void logError(Exception e) {
    LoggerUtil.getLogger().error("!!!!!!!!! Parse error: " + e.toString() + ", file: " + inXmlFile);
  }
}
