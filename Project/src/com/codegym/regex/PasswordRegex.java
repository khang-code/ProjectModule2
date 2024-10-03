package com.codegym.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordRegex {
    private static final String PASSWORD_REGEX = "^([a-z]|[A-Z]|[0-9]|\\W)\\S{6,24}$";

    public PasswordRegex() {
    }
    public boolean passwordValidate(String regex) {
        Pattern pattern = Pattern.compile(PASSWORD_REGEX);
        Matcher matcher = pattern.matcher(regex);
        return matcher.matches();
    }
}

