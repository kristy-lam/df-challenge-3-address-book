package com.dfcorp.app;

import org.apache.commons.validator.routines.EmailValidator;
import org.junit.platform.commons.util.StringUtils;
import java.util.regex.*;

public class Contact {

    private String name;
    private String phoneNumber;
    private String emailAddress;

    public Contact (String name, String phoneNumber, String emailAddress) {
        this.name = validateString(name);
        this.phoneNumber = validatePhoneNumber(phoneNumber);
        this.emailAddress = validateEmailAddress(emailAddress);
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    private static String validateString(String stringToValidate) {
        if (stringToValidate == null || StringUtils.isBlank(stringToValidate)) throw new IllegalArgumentException(
                "Blank input is not allowed, please try again."
        );
        return stringToValidate;
    }

    private static String validatePhoneNumber(String phoneNumberToValidate) {
        String result = validateString(phoneNumberToValidate);
        // Allow 10 or 11 digits (depends on whether 0 at the start is included)
        Pattern pattern = Pattern.compile("^\\d{10,11}$");
        Matcher matcher = pattern.matcher(result);
        if (!(matcher.matches())) throw new IllegalArgumentException(
                "Phone number inputted is not in the correct format, please try again.");
        return result;
    }

    private static String validateEmailAddress(String emailAddressToValidate) {
        String result = validateString(emailAddressToValidate);
        // Use "commons-validator" dependency to validate email address
        EmailValidator emailValidator = EmailValidator.getInstance();
        if (!(emailValidator.isValid(emailAddressToValidate))) throw new IllegalArgumentException(
                "Email address inputted is not in the correct format, please try again."
        );
        return result;
    }

    @Override
    public String toString() {
        return "Contact { " +
                "name=" + name +
                ", phoneNumber=" + phoneNumber +
                ", emailAddress=" + emailAddress +
                " }\n";
    }
}
