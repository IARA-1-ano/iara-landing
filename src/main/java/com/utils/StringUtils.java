package com.utils;

public class StringUtils {
  public static String capitalize(String s) {
    if (s.length() < 2) {
      return s.toUpperCase();
    }

    s = s.replace("_", " ");
    return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
  }
}
