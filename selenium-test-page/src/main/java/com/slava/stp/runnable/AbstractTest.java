package com.slava.stp.runnable;

import com.slava.stp.log.LoggerUtil;
import com.slava.stp.shell.SeleniumShell;
import com.slava.stp.util.FileUtil;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public abstract class AbstractTest implements TestPage {

    private Logger logger;
    private File testFolder;

    public AbstractTest() {
    }

    @Override
    public void setTestPageFolder(File testFolder) {
        this.testFolder = testFolder;
    }

    public File getTestPageFolder() {
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
