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

    private Contact findContact(String inputType, String searchInput) throws IllegalArgumentException {
        for (Contact contact : allContacts) {
            if (("name".equals(inputType) && contact.getName().equals(searchInput)) ||
                    ("phoneNumber".equals(inputType) && contact.getPhoneNumber().equals(searchInput))) {
                return contact;
            }
        }
        throw new IllegalArgumentException("Contact is not found.");
    }

    public String searchContact(String inputType, String searchInput) {
        Contact targetContact = findContact(inputType, searchInput);
        return targetContact.toString();
    }

    public void editContact(String detailType, String oldDetail, String newDetail) {

        Contact contactToEdit = findContact(detailType, oldDetail);

        if (detailType == "name") {
            newDetail = contactToEdit.validateString(newDetail);
            contactToEdit.setName(newDetail);
        }

        if (detailType == "phoneNumber") {
            newDetail = contactToEdit.validatePhoneNumber(newDetail);
            contactToEdit.setPhoneNumber(newDetail);
        }
    }

}
