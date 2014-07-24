package com.slava.stp.log;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import com.slava.stp.util.FileUtil;
import com.slava.stp.util.StringUtil;

public class LoggerUtil {

    private static String workDir = ".";

    private static Date date;

    private static final LinkedList<LoggerConfig> stackLogger = new LinkedList<LoggerConfig>();

    public static void setWorkDir(String workDir) {
        LoggerUtil.workDir = workDir;
    }

    public static void setLogger(String absoluteClassName, String level, String pattern) throws IOException {
        Logger logger = Logger.getLogger(absoluteClassName);
        Appender appender = logger.getAppender(absoluteClassName);
        if (appender == null) {
            if (stackLogger.size() == 0) {
                date = new Date();
            }
            appender = getAppender(absoluteClassName);
            appender.setName(absoluteClassName);
            logger.addAppender(appender);
        }
        stackLogger.add(new LoggerConfig(logger, level, pattern));
    }

    public static void removeLogger() {
        stackLogger.removeLast();
        try {
            stackLogger.getLast().config();
        } catch (NoSuchElementException e) {
        }
    }

    public static Logger getLogger() {
        try {
            return stackLogger.getLast().getLogger();
        } catch (NoSuchElementException e) {
            return Logger.getRootLogger();
        }
    }

    private static Appender getAppender(String absoluteClassName) throws IOException {
        String className = absoluteClassName;
        String packageName = "";
        String[] splitByLast = StringUtil.getSplitByLast(absoluteClassName, ".");
        if (splitByLast != null) {
            packageName = splitByLast[0];
            className = splitByLast[1];
        }
        File rootFolder = FileUtil.getFolderByRootPackage(workDir, packageName);
        return new FileAppender(new PatternLayout("[%-5p] %m%n"), FileUtil.getFileNameByDate(date, rootFolder, className, ".log"));
    }
}
