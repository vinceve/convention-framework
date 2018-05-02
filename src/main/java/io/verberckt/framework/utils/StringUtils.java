package io.verberckt.framework.utils;

public class StringUtils {
    public static String capitalize(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
}
