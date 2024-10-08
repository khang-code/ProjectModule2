package com.codegym.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GmailRegex {
    private static final String GMAIL_REGEX = "^([a-z]|[A-Z]|[0-9]){6,20}+@(gmail)+(\\.(com))$";

    public GmailRegex() {
    }
    public static boolean gmailValidate(String regex) {
        Pattern pattern = Pattern.compile(GMAIL_REGEX);
        Matcher matcher = pattern.matcher(regex);
        return matcher.matches();
    }
}
