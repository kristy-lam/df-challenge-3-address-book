package com.dfcorp.app;

import org.junit.platform.commons.util.StringUtils;

public class Contact {

    private String name;
    private String phoneNumber;
    private String emailAddress;

    public Contact (String name, String phoneNumber, String emailAddress) {
        validateString(name);
        this.name = name;
        validatePhoneNumber(phoneNumber);
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
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
        return validateString(phoneNumberToValidate);
    }
}
