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

    private boolean checkNotDuplicate(Contact contactToBeChecked) {
        ArrayList<Contact> contacts = this.getAllContacts();
        for (Contact contact : contacts) {
            if (contactToBeChecked.getPhoneNumber().equals(
                    contact.getPhoneNumber())) throw new IllegalArgumentException();
            if (contactToBeChecked.getEmailAddress().equals(
                    contact.getEmailAddress())) throw new IllegalArgumentException();
        }
        return true;
    }

    public void addContact(Contact contactToAdd) {
        checkNotDuplicate(contactToAdd);
        allContacts.add(contactToAdd);
        System.out.println("Contact has been added.");
    }

    private Contact searchContact(String inputType, String searchInput) throws IllegalArgumentException {
        for (Contact contact : allContacts) {
            if (("name".equals(inputType) && contact.getName().equals(searchInput)) ||
                    ("phoneNumber".equals(inputType) && contact.getPhoneNumber().equals(searchInput)) ||
                    ("emailAddress".equals(inputType) && contact.getEmailAddress().equals(searchInput))) {
                return contact;
            }
        } throw new IllegalArgumentException("Contact is not found.");
    }

    public String viewContact(String inputType, String searchInput) {
        Contact targetContact = searchContact(inputType, searchInput);
        return targetContact.toString();
    }

    public void editContact(String detailType, String oldDetail, String newDetail) {
        Contact contactToEdit = searchContact(detailType, oldDetail);
        if (detailType == "name") contactToEdit.setName(newDetail);
        if (detailType == "phoneNumber") contactToEdit.setPhoneNumber(newDetail);
        if (detailType == "emailAddress") contactToEdit.setEmailAddress(newDetail);
        System.out.printf("Contact's %s has been updated to %s.", detailType, newDetail);
    }

    public void removeContact(String detailType, String removeInput) {
        Contact contactToRemove = searchContact(detailType, removeInput);
        allContacts.remove(contactToRemove);
    }
}
