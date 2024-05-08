package com.dfcorp.app;

import org.junit.platform.commons.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contact {

    private String name;
    private String phoneNumber;
    private String emailAddress;

    public Contact (String name, String phoneNumber, String emailAddress) {
        this.name = validateString(name);
        this.phoneNumber = validatePhoneNumber(phoneNumber);
        this.emailAddress = validateString(emailAddress);
        String contactAddedMsg = "Contact has been added.";
        System.out.println(contactAddedMsg);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    private static String validateString(String stringToValidate) {
        if (stringToValidate == null || StringUtils.isBlank(stringToValidate)) throw new IllegalArgumentException();
        return stringToValidate;
    }

    private static String validatePhoneNumber(String phoneNumberToValidate) {
        String result = validateString(phoneNumberToValidate);
        // Allow 10 or 11 digits (depends on whether 0 at the start is included)
        Pattern pattern = Pattern.compile("^\\d{10,11}$");
        Matcher matcher = pattern.matcher(result);
        if (!(matcher.matches())) throw new IllegalArgumentException();
        return result;
    }
}
