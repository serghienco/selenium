package com.slava.stp.runnable;

import java.io.File;

public interface TestPage {

    Object run(Object... arguments);

    void setTestPageFolder(File testPageFolder);
}
