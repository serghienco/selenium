package com.slava.stp.runnable;

import java.io.File;

public interface Test {

  Object run(Object... arguments);
  
  void setTestFolder(File testFolder) ;
}
