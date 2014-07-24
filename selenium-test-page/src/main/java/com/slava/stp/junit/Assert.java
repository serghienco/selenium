package com.slava.stp.junit;

import junit.framework.ComparisonFailure;

public class Assert extends junit.framework.Assert {

    public static boolean isEquals(Object expected, Object actual) {
        if ((expected == null) && (actual == null)) return true;
        if ((expected != null) && (expected.equals(actual))) return true;
        return false;
    }

    public static void failEquals(String message, Object expected, Object actual) {
        fail(formatNot(message, expected, actual));
    }

    public static String formatNot(String message, Object expected, Object actual) {
        String formatted = "";
        if ((message != null) && (message.length() > 0)) formatted = message + " ";
        return formatted + "expected and actual values is equals - expected:<" + expected + "> but was:<" + actual + ">";
    }

    public static void assertNotEquals(String message, Object expected, Object actual) {
        if (isEquals(expected, actual)) {
            failEquals(message, expected, actual);
        }
    }

    public static void assertNotEquals(Object expected, Object actual) {
        assertNotEquals(null, expected, actual);
    }

    public static void assertNotEquals(String message, String expected, String actual) {
        if (isEquals(expected, actual)) {
            String cleanMessage = (message == null) ? "" : message;
            throw new ComparisonFailure(cleanMessage, expected, actual);
        }
    }

    public static void assertNotEquals(String expected, String actual) {
        assertNotEquals(null, expected, actual);
    }
}
