package com.solutions.medilab.backend.utils;

public class Utils {
    private Utils() {
        // Default constructor
    }

    public static boolean isNotBlank(String val) {
        return !isBlank(val);
    }

    public static  boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }

    public static <T> boolean isNull(T val) {
        return null==val;
    }

    public static <T> boolean isNotNull(T val) {
        return !isNull(val);
    }
}
