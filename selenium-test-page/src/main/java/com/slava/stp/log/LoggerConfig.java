package com.slava.stp.log;

import java.util.Enumeration;

import org.apache.log4j.Appender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class LoggerConfig {

  private Level level;
  private String layout;
  private Logger logger;

  public LoggerConfig(Logger logger, String level, String layout) {
    this.logger = logger;
    this.level = Level.toLevel(level, Level.toLevel(System.getProperty("test.log.level"), Level.INFO));
    this.layout = (layout == null || layout.trim().length() == 0) ? "[%-5p] %m%n" : layout;
    config();
  }

  public Level getLevel() {
    return level;
  }

  public String getLayout() {
    return layout;
  }

  public Logger getLogger() {
    return logger;
  }

  public void config(Logger logger) {
    logger.setLevel(level);
    Enumeration<Appender> allAppenders = logger.getAllAppenders();
    while (allAppenders.hasMoreElements()) {
      Appender appender = (Appender) allAppenders.nextElement();
      appender.setLayout(new PatternLayout(layout));
    }
  }

  public void config() {
    config(logger);
    config(Logger.getRootLogger());
  }
}
