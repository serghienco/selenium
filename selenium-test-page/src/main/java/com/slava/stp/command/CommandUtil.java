package com.slava.stp.command;

import java.text.MessageFormat;

import com.slava.stp.Driver.CommandType;
import com.slava.stp.command.$common.CommonAbstract;
import com.slava.stp.exception.CommandInitiateException;
import com.slava.stp.exception.NotImplementedCommandException;
import com.slava.stp.util.StringUtil;

public class CommandUtil {

  private static String ROOT_PATH = "com.slava.stp.commands.";

  private static String CLASS_NAME_PATTERN = ROOT_PATH + "${0}.{1}";
  private static String DEFAULT_CLASS_NAME = "Default";

  public static Command newInstance(Command parentCommand, CommandType type, String name) {
    String className = MessageFormat.format(CLASS_NAME_PATTERN, type, StringUtil.toUpperCaseFirst(name));
    try {
      return (Command) Class.forName(className).newInstance();
    } catch (InstantiationException e) {
      throw new CommandInitiateException(type, name, className);
    } catch (IllegalAccessException e) {
      throw new CommandInitiateException(type, name, className);
    } catch (ClassNotFoundException e) {
      if (name.equals(DEFAULT_CLASS_NAME)) {
        throw new NotImplementedCommandException(parentCommand, type, name, className);
      }
      return newInstance(parentCommand, type, DEFAULT_CLASS_NAME);
    }
  }

  public static CommonAbstract newCommonInstance(Command parentCommand, String name) {
    return (CommonAbstract) newInstance(parentCommand, CommandType.COMMON_TYPE, name);
  }

  public static Command newAssertInstance(Command parentCommand, String name) {
    return newInstance(parentCommand, CommandType.ASSERT_TYPE, name);
  }
}
