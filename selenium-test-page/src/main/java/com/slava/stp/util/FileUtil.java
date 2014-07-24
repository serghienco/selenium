package com.slava.stp.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.slava.stp.util.filefilter.FilePatternFilter;
import com.slava.stp.util.filefilter.FolderFilter;

public class FileUtil {

  private static FileFilter folderFilter = new FolderFilter();

  public static String getFileClassName(File file) {
    String name = file.getName();
    int index = name.lastIndexOf(".");
    if (index != -1) {
      name = name.substring(0, index);
    }
    return StringUtil.getJavaClassName(name);
  }

  public static File getFolderByRootPackage(String rootFile, String packageFile) {
    if (packageFile == null) {
      packageFile = "";
    }
    if (rootFile == null) {
      rootFile = ".";
    }
    File file = new File(rootFile + "/" + packageFile.replace(".", "/"));
    if (!file.isDirectory()) {
      file.mkdirs();
    }
    return file;
  }

  public static File getFile(File folder, String name, String ext) {
    return new File(folder, name + "." + ext);
  }

  public static void newFile(File file) throws IOException {
    if (file.isFile()) {
      file.delete();
    }
    file.createNewFile();
  }

  public static BufferedWriter getWriterUTF8(File file) throws UnsupportedEncodingException, FileNotFoundException {
    return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
  }

  public static String getFileNameByDate(Date date, File root, String name, String sufix) {
    return root.getAbsolutePath() + "/" + new SimpleDateFormat("yyyyMMdd_HHmmss_SSS_").format(date) + name + sufix;
  }

  public static String getFileName(String file) {
    return new File(file).getName();
  }

  public static void copyStreams(InputStream is, OutputStream os) throws IOException {
    byte[] buffer = new byte[4096];
    int bytesRead;
    while ((bytesRead = is.read(buffer)) != -1) {
      os.write(buffer, 0, bytesRead);
    }
  }

  public static String[] getFiles(File parent, String folder, String fileNamePattern, boolean recursiv) {
    File root = new File(parent, folder);
    if (!root.isDirectory()) {
      throw new IllegalArgumentException("Don't exist the folder name: " + root);
    }
    return getFilesRecursiv(root, new FilePatternFilter(fileNamePattern), recursiv).toArray(new String[0]);
  }

  private static List<String> getFilesRecursiv(File folder, FileFilter fileNamePattern, boolean recursiv) {
    List<String> list = new LinkedList<String>();
    for (File f : folder.listFiles(fileNamePattern)) {
      list.add(f.getAbsolutePath());
    }
    if (recursiv) {
      for (File childFolder : folder.listFiles(folderFilter)) {
        list.addAll(getFilesRecursiv(childFolder, fileNamePattern, true));
      }
    }
    return list;
  }

  public static String getImageName(String prefix, String sufix) {
    return MessageFormat.format("{0}{1,time,yyyyMMdd_HHmmss_SSS}{2}", prefix, new Date(), sufix);
  }
}
