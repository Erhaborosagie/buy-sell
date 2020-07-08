package com.osagieerhabor.backend.utils;

import java.util.regex.Pattern;

public class StringUtils {
    public static String generateSlug(String title) {
        return generateSlug(title, "-");
    }

    public static String generateSlug(String title, String delimiter) {
        return generateSlug(title,delimiter,"()[]{}=?!.:,-_+\\\"#~/");
    }

    public static String generateSlug(String title, String delimiter, String throwAwayChars) {
        if (title == null) return "";

        title = title.replaceAll("[" + Pattern.quote(throwAwayChars) + "]", "");
        return String.join(delimiter, title.split(" ")).toLowerCase();
    }
}
