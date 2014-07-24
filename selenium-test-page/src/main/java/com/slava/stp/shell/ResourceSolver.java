package com.slava.stp.shell;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.slava.stp.exception.SeleniumException;
import com.slava.stp.util.FileUtil;

public class ResourceSolver {

  private String inFile;
  private String outFolder;

  public ResourceSolver(String inFile, String outFolder) {
    this.inFile = inFile;
    this.outFolder = outFolder;
  }

  public void solve() {
    String fileName = FileUtil.getFileName(inFile);
    File outFolderFile = new File(outFolder);
    File outFile = new File(outFolderFile, fileName);
    if (!outFile.isFile()) {
      if (!outFolderFile.isDirectory()) {
        outFolderFile.mkdirs();
      }
      InputStream resource = null;
      FileOutputStream outputStream = null;
      try {
        outFile.createNewFile();
        resource = getClass().getClassLoader().getResourceAsStream(inFile);
        outputStream = new FileOutputStream(outFile);
        FileUtil.copyStreams(resource, outputStream);
      } catch (IOException e) {
        throw new SeleniumException(e.getMessage(), e);
      } finally {
        try {
          outputStream.close();
        } catch (Exception e) {
        }
        try {
          resource.close();
        } catch (Exception e) {
        }
      }
    }
  }
}
