package com.dfcorp.app;

public class Contact {

    private String name;
    private String phoneNumber;
    private String emailAddress;

    public Contact (String name, String phoneNumber, String emailAddress) {
        validateName(name);
        this.name = name;
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

    private static String validateName(String nameToValidate) {
        if (nameToValidate == null) throw new IllegalArgumentException();
        return nameToValidate;
    }
}
