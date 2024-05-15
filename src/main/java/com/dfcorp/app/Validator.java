package com.dfcorp.app;

import org.apache.commons.validator.routines.EmailValidator;
import org.junit.platform.commons.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public static void validateString(String stringToValidate) {
        if (stringToValidate == null || StringUtils.isBlank(stringToValidate)) throw
                new IllegalArgumentException(
                "Blank input is not allowed, please try again.");
    }

    public static void validatePhoneNumber(String phoneNumberToValidate) {
        validateString(phoneNumberToValidate);
        // Allow 10 or 11 digits (depends on whether 0 at the start is included)
        Pattern pattern = Pattern.compile("^\\d{10,11}$");
        Matcher matcher = pattern.matcher(phoneNumberToValidate);
        if (!(matcher.matches())) throw new IllegalArgumentException(
                "Phone number inputted is not in the correct format, please try again.");
    }

    public static void validateEmailAddress(String emailAddressToValidate) {
        validateString(emailAddressToValidate);
        // Use "commons-validator" dependency to validate email address
        EmailValidator emailValidator = EmailValidator.getInstance();
        if (!(emailValidator.isValid(emailAddressToValidate))) throw
                new IllegalArgumentException(
                "Email address inputted is not in the correct format, please try again.");
    }

    public static void validateType(String typeToValidate) {
        validateString(typeToValidate);
        if (!typeToValidate.equals("name") && !typeToValidate.equals("phoneNumber") &&
                !typeToValidate.equals("emailAddress")) throw
        new IllegalArgumentException("Invalid type, please try again.");
    }
}
