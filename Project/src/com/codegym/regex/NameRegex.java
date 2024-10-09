package com.codegym.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameRegex {
    private static final String NAME_REGEX = "^([a-z]|[A-Z]|[0-9]|\\W)\\S{6,24}$";

    public NameRegex() {
    }
    public boolean passwordValidate(String regex) {
        Pattern pattern = Pattern.compile(NAME_REGEX);
        Matcher matcher = pattern.matcher(regex);
        return matcher.matches();
    }
}
