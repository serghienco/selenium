package com.slava.stp.util;

public class StringUtil {

    public static String trimEmptyToNull(String s) {
        return (s == null || (s = s.trim()).length() > 0) ? s : null;
    }

    public static boolean isEmptyOrNull(String s) {
        return s == null || s.length() == 0 || s.trim().length() == 0;
    }

    public static String toString(String text) {
        if (text == null) {
            text = "";
        }
        return "'" + text.replaceAll("\\\\", "\\\\\\\\").replaceAll("'", "\\\\'") + "'";
    }

    public static String toGString(String text) {
        if (text == null) {
            text = "";
        }
        return "\"\"\"\\\n" + text + "\"\"\""; //text.replaceAll("\\\\", "\\\\\\\\")
    }

    public static String toGString(Object text) {
        if (text == null) {
            text = "";
        }
        return toGString(text.toString());
    }

    public static String toUpperCaseFirst(String text) {
        char firstChar = Character.toUpperCase(text.charAt(0));
        return firstChar + text.substring(1);
    }

    public static String replaceNotIdentifier(String text, String replacement) {
        return text.replaceAll("[^$^_^0-9^A-Z^a-z]", replacement);
    }

    public static String getJavaClassName(String text) {
        String className = text.replaceAll("[^_^$^0-9^A-Z^a-z]", "");
        int i = 0;
        for (int len = className.length(); i < len; i++) {
            char charAt = className.charAt(i);
            if (!(charAt > 47 && charAt < 58)) {
                break;
            }
        }
        className = className.substring(i, className.length());
        return toUpperCaseFirst(className);
    }

    public static String[] getSplitByFirst(String text, String token) {
        int index = text.indexOf(token);
        if (index != -1) {
            return new String[]{
                    text.substring(0, index), text.substring(index + 1)
            };
        }
        return null;
    }

    public static String[] getSplitByLast(String text, String token) {
        int index = text.lastIndexOf(token);
        if (index != -1) {
            return new String[]{
                    text.substring(0, index), text.substring(index + 1)
            };
        }
        return null;
    }

    public static String concatByString(String str1, String str2, String join) {
        if (str1.endsWith(join)) {
            str1 = str1.substring(0, str1.length() - join.length());
        }
        if (str2.startsWith(join)) {
            str2 = str2.substring(join.length());
        }
        return new StringBuffer(str1.length() + str2.length() + join.length()).append(str1).append(join).append(str2).toString();
    }

    public static String join(String join, String... strs) {
        String resp = "";
        boolean first = true;
        for (String s : strs) {
            if (first) {
                first = false;
            } else {
                resp += join;
            }
            resp += s;
        }
        return resp;
    }

    public static String replaceLastIf(String text, String oldLast, String newLast) {
        if (text.endsWith(oldLast)) {
            text = text.substring(0, text.length() - oldLast.length()) + newLast;
        }
        return text;
    }

    public static String removeBegin(String text, String begin) {
        return text.substring(begin.length());
    }
}
