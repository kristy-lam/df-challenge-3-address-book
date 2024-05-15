package com.dfcorp.app;

import java.util.*;

public class AddressBook {

    private final ArrayList<Contact> allContacts;

    public AddressBook() {
        allContacts = new ArrayList<>();
    }

    // Kept for testing purpose only to maintain encapsulation
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

    private void checkNotDuplicate(Contact contactToBeChecked) {
        ArrayList<Contact> contacts = allContacts;
        for (Contact contact : contacts) {
            if (contact != contactToBeChecked) {
                if (contactToBeChecked.getPhoneNumber().equals(contact.getPhoneNumber())) {
                    throw new IllegalArgumentException(
                            "This phone number already appears in another contact in the address book.");
                }
                if (contactToBeChecked.getEmailAddress().equals(contact.getEmailAddress())) {
                    throw new IllegalArgumentException(
                            "This email address already appears in another contact in the address book.");
                }
            }
        }
    }

    public void addContact(Contact contactToAdd) {
        checkNotDuplicate(contactToAdd);
        allContacts.add(contactToAdd);
        System.out.println("Contact has been added.\n");
    }

    private Contact searchContact(String inputType, String searchInput) throws IllegalArgumentException {
        for (Contact contact : allContacts) {
            if (("name".equals(inputType) && contact.getName().contains(searchInput)) ||
                    ("phoneNumber".equals(inputType) && contact.getPhoneNumber().contains(searchInput)) ||
                    ("emailAddress".equals(inputType) && contact.getEmailAddress().contains(searchInput))) {
                return contact;
            }
        } throw new IllegalArgumentException("Contact is not found.");
    }

    private ArrayList<Contact> searchAllContacts(String inputType, String searchInput) {
        ArrayList<Contact> matchedContacts = new ArrayList<>();
        for (Contact contact : allContacts) {
            if (("name".equals(inputType) && contact.getName().contains(searchInput)) ||
                    ("phoneNumber".equals(inputType) && contact.getPhoneNumber().contains(searchInput)) ||
                    ("emailAddress".equals(inputType) && contact.getEmailAddress().contains(searchInput))) {
                matchedContacts.add(contact);
            }
        }
        matchedContacts.sort(Comparator.comparing((Contact c) -> c.getName().toLowerCase()));
        return matchedContacts;
    }

    public String viewContact(String inputType, String searchInput) throws Exception {
        ArrayList<Contact> matchedContacts = searchAllContacts(inputType, searchInput);
        if (matchedContacts.isEmpty()) throw new Exception("No contacts found.");

        StringBuilder result = new StringBuilder("Matched Contact(s):\n");
        for (Contact contact : matchedContacts) {
            result.append(contact.toString());
        } return result.toString();
    }


    public void editContact(String detailType, String oldDetail, String newDetail) {
        Contact contactToEdit = searchContact(detailType, oldDetail);
        if (detailType.equals("name")) contactToEdit.setName(newDetail);
        if (detailType.equals("phoneNumber")) contactToEdit.setPhoneNumber(newDetail);
        if (detailType.equals("emailAddress")) contactToEdit.setEmailAddress(newDetail);
        checkNotDuplicate(contactToEdit);
        System.out.printf("Contact's %s has been updated to %s.\n", detailType, newDetail);
    }

    public void removeContact(String detailType, String removeInput) {
        Contact contactToRemove = searchContact(detailType, removeInput);
        allContacts.remove(contactToRemove);
        System.out.println("Contact has been removed.\n");
    }

    public void deleteAllContacts() throws Exception{
        if (allContacts.isEmpty()) throw new Exception("Address book is empty.");
        allContacts.clear();
    }
}
