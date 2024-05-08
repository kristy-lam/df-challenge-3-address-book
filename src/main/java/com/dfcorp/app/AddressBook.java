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

    public void addContact(Contact contact) {
        allContacts.add(contact);
        String contactAddedMsg = "Contact has been added.";
        System.out.println(contactAddedMsg);
    }
}
