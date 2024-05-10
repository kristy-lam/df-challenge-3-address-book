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

    public ArrayList<String> viewAllContacts() throws Exception {
        ArrayList<String> result = new ArrayList<>();
        for (Contact contact : allContacts) {
            result.add(contact.toString());
        }
        if (result.isEmpty()) throw new Exception("Address book is empty.");
        return result;
    }

    private Contact checkNotDuplicate(Contact contactToBeChecked) {
        ArrayList<Contact> contacts = this.getAllContacts();
        for (Contact contact : contacts) {
            if (contactToBeChecked.getPhoneNumber().equals(
                    contact.getPhoneNumber())) throw new IllegalArgumentException();
            if (contactToBeChecked.getEmailAddress().equals(
                    contact.getEmailAddress())) throw new IllegalArgumentException();
        }
        return contactToBeChecked;
    }

    public void addContact(Contact contact) {
        Contact checkedContact = checkNotDuplicate(contact);
        allContacts.add(checkedContact);
        String contactAddedMsg = "Contact has been added.";
        System.out.println(contactAddedMsg);
    }

    private Contact findContactByName(String nameInput) throws IllegalArgumentException {
        Contact targetContact;
        for (Contact contact : allContacts) {
            if (contact.getName() == nameInput) {
                targetContact = contact;
                return targetContact;
            }
        }
        throw new IllegalArgumentException("Name is not found.");
    }

    public String searchContactByName(String nameInput) {
        Contact targetContact = findContactByName(nameInput);
        return targetContact.toString();
    }

    public void editContact(String detailType, String oldDetail, String newDetail) {
        if (detailType == "name") {
            Contact contactToEdit = findContactByName(oldDetail);
            contactToEdit.setName(newDetail);
        }
    }
}
