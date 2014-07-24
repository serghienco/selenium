package com.slava.stp.util.filefilter;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Pattern;

public class FilePatternFilter implements FileFilter {

  private Pattern p;

  public FilePatternFilter(String pattern) {
    p = Pattern.compile(pattern);
  }

  @Override
  public boolean accept(File file) {
    return file.isFile() && p.matcher(file.getName()).matches();
  }
}
