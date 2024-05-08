package com.dfcorp.app;

import java.util.ArrayList;

public class AddressBook {

    private ArrayList<Contact> allContacts;

    public AddressBook() {
        allContacts = new ArrayList<>();
    }

    public ArrayList<Contact> getAllContacts() {
        return allContacts;
    }

    private Contact checkIsDuplicate(Contact contactToBeChecked) {
        ArrayList<Contact> contacts = this.getAllContacts();
        for (Contact contact : contacts) {
            if (contactToBeChecked.getPhoneNumber() == contact.getPhoneNumber()) throw new IllegalArgumentException();
            if (contactToBeChecked.getEmailAddress() == contact.getEmailAddress()) throw new IllegalArgumentException();
        }
        return contactToBeChecked;
    }

    public void addContact(Contact contact) {
        checkIsDuplicate(contact);
        allContacts.add(contact);
        String contactAddedMsg = "Contact has been added.";
        System.out.println(contactAddedMsg);
    }
}
