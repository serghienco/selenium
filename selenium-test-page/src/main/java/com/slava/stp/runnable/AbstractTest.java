package com.slava.stp.runnable;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import com.slava.stp.log.LoggerUtil;
import com.slava.stp.shell.SeleniumShell;
import com.slava.stp.util.FileUtil;

public abstract class AbstractTest implements Test {

  public AbstractTest() {
  }

  private Logger logger;
  private File testFolder;

  public void setTestFolder(File testFolder) {
    this.testFolder = testFolder;
  }

  public File getTestFolder() {
    return testFolder;
  }

  public void runSubTest(String file, boolean absolute, boolean required, Object... arguments) throws SAXException, IOException, ParserConfigurationException, InstantiationException, IllegalAccessException, ClassNotFoundException {
    File f = ((absolute) ? new File(file) : new File(testFolder, file));

    if (!required && !f.isFile()) {
      logger.warn("Can't load the subtest, file is not exist: " + f.getPath());
      return;
    } else {
      logger.info("Prepare for load the subtest file: " + f.getPath());
    }

    SeleniumShell.getInstance().run(f.getPath(), arguments);
  }

  public void init(String level, String pattern, Object... arguments) throws IOException {
    LoggerUtil.setLogger(this.getClass().getCanonicalName(), level, pattern);
    logger = LoggerUtil.getLogger();
    if (logger.isInfoEnabled()) {
      logger.info("\n\n>>>>>>>> Init test: " + getClass().getName() + ", arguments: " + Arrays.toString(arguments));
    }
  }

  public void exception(Throwable t) {
    LoggerUtil.getLogger().error(t.getMessage(), t);
  }

  public void destory() {
    if (logger != null) {
      logger.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
      LoggerUtil.removeLogger();
      logger = null;
    }
  }

  public Logger getLogger() {
    return logger;
  }

  public String[] getFiles(String folder, String namePattern, boolean recursiv) {
    return FileUtil.getFiles(testFolder, folder, namePattern, recursiv);
  }

  public void log(String level, String message) {
    logger.log(Level.toLevel(level, Level.INFO), message);
  }
}
