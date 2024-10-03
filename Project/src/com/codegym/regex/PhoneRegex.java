package com.codegym.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneRegex {
    //Vietnam phone only
    private static final String PHONE_REGEX = "^(\\(\\+84\\)|0)+[0-9]{9}$";

    public PhoneRegex() {
    }
    public boolean phoneValidate(String regex) {
        Pattern pattern = Pattern.compile(PHONE_REGEX);
        Matcher matcher = pattern.matcher(regex);
        return matcher.matches();
    }
}
