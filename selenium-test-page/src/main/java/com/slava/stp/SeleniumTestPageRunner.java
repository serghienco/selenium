package com.slava.stp;

import com.slava.stp.log.LoggerUtil;
import com.slava.stp.shell.SeleniumShell;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileFilter;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by slavic on 7/24/14.
 */
public class SeleniumTestPageRunner {

    private final static ArgumentsParser parser = new ArgumentsParser();

    private static class AcceptFolderFilter implements FileFilter {
        @Override
        public boolean accept(File file) {
            return file.isDirectory();
        }
    }

    private static class AcceptFilesByPatternFilter implements FileFilter {

        private Pattern pattern;

        public void setPattern(String pattern) {
            this.pattern = Pattern.compile(pattern);
        }

        @Override
        public boolean accept(File file) {
            return file.isFile() && pattern.matcher(file.getName()).matches();
        }
    }

    private static AcceptFolderFilter ACCEPT_FOLDERS_FILTER = new AcceptFolderFilter();
    private static AcceptFilesByPatternFilter ACCEPT_FILES_BY_PATTERN_FILTER = new AcceptFilesByPatternFilter();


    private static final Logger logger = LoggerUtil.getLogger();

    private static final SeleniumShell seleniumShell = SeleniumShell.getInstance();

    public static void main(String[] args) {
        logger.info(">>> Begin tests runner:");
        try {
            parser.parse(args);
            if (logger.isInfoEnabled()) {
                logger.info(">>> Args: " + Arrays.toString(args));
                logger.info(">>> Conf: " + parser);
            }
            execute(parser.getRoot(), parser.getNames(), parser.isRecursive(), parser.getArguments());
            logger.info(">>> Succesful!!!");
        } catch (Throwable t) {
            logger.fatal(t.toString(), t);
        }
        logger.info("<<< End.\n\n\n\n\n\n\n");
    }

    private static void execute(File root, List<String> filters, boolean recursiv, Object[] arguments) {
        Set<File> files = new HashSet<File>();
        for (String filter : filters) {
            ACCEPT_FILES_BY_PATTERN_FILTER.setPattern(filter);
            for (File file : root.listFiles(ACCEPT_FILES_BY_PATTERN_FILTER)) {
                files.add(file);
            }
        }


        for (File file : files) {
            if (logger.isInfoEnabled()) {
                logger.info("########################## Begin the test - " + file.getPath() + " ##########################");
            }
            try {
                seleniumShell.run(file.getPath(), arguments);
            } catch (Exception e) {
                logger.error("########################## Failed the test - " + file.getPath() + ", Exception: " + e.toString(), e);
            }
            if (logger.isInfoEnabled()) {
                logger.info("########################## End the test - " + file.getPath() + " ##########################\n\n\n");
            }
        }

        if (recursiv) {
            for (File folder : root.listFiles(ACCEPT_FOLDERS_FILTER)) {
                execute(folder, filters, true, arguments);
            }
        }
    }
}
