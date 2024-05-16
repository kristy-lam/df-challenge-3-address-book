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

    private void checkDuplicatePhoneNumber(Contact contactToBeChecked) {
        for (Contact contact : allContacts) {
            if (contact != contactToBeChecked &&
                    contactToBeChecked.getPhoneNumber().equals(contact.getPhoneNumber())) {
                throw new IllegalArgumentException(
                        "This phone number already appears in another contact in the address book.");
            }
        }
    }

    private void checkDuplicateEmailAddress(Contact contactToBeChecked) {
        for (Contact contact : allContacts) {
            if (contact != contactToBeChecked &&
                    contactToBeChecked.getEmailAddress().equals(contact.getEmailAddress())) {
                throw new IllegalArgumentException(
                        "This email address already appears in another contact in the address book.");
            }
        }
    }

    private void checkNotDuplicate(Contact contactToBeChecked) {
        checkDuplicatePhoneNumber(contactToBeChecked);
        checkDuplicateEmailAddress(contactToBeChecked);
    }

    public void addContact(Contact contactToAdd) {
        checkNotDuplicate(contactToAdd);
        allContacts.add(contactToAdd);
        System.out.println("Contact has been added.\n");
    }

    private Contact searchByCriteria(String criteria, String searchInput) {
        for (Contact contact : allContacts) {
            if (("name".equals(criteria) && contact.getName().contains(searchInput)) ||
                    ("phoneNumber".equals(criteria) && contact.getPhoneNumber().contains(searchInput)) ||
                    ("emailAddress".equals(criteria) && contact.getEmailAddress().contains(searchInput))) {
                return contact;
            }
        } return null;
    }

    private Contact searchContact(String inputType, String searchInput) throws IllegalArgumentException {
        Contact foundContact = searchByCriteria(inputType, searchInput);
        if (foundContact != null) return foundContact;
        else throw new IllegalArgumentException("Contact is not found.");
    }

    private boolean matchesInputType(Contact contact, String inputType, String searchInput) {
        return ("name".equals(inputType) && contact.getName().contains(searchInput)) ||
                ("phoneNumber".equals(inputType) && contact.getPhoneNumber().contains(searchInput)) ||
                ("emailAddress".equals(inputType) && contact.getEmailAddress().contains(searchInput));
    }

    private void addMatchedContacts(ArrayList<Contact> matchedContacts, String inputType, String searchInput) {
        for (Contact contact : allContacts) {
            if (matchesInputType(contact, inputType, searchInput)) {
                matchedContacts.add(contact);
            }
        }
    }

    private void sortContactsByName(ArrayList<Contact> matchedContacts) {
        matchedContacts.sort(Comparator.comparing(contact -> contact.getName().toLowerCase()));
    }

    private ArrayList<Contact> searchAllContacts(String inputType, String searchInput) {
        ArrayList<Contact> matchedContacts = new ArrayList<>();
        addMatchedContacts(matchedContacts, inputType, searchInput);
        sortContactsByName(matchedContacts);
        return matchedContacts;
    }

    private ArrayList<Contact> findMatchedContacts(String inputType, String searchInput) throws Exception {
        ArrayList<Contact> matchedContacts = searchAllContacts(inputType, searchInput);
        if (matchedContacts.isEmpty()) {
            throw new Exception("No contacts found.");
        }
        return matchedContacts;
    }

    private String formatContacts(ArrayList<Contact> contacts) {
        StringBuilder result = new StringBuilder("Matched Contact(s):\n");
        for (Contact contact : contacts) {
            result.append(contact.toString());
        }
        return result.toString();
    }

    public String viewContact(String inputType, String searchInput) throws Exception {
        ArrayList<Contact> matchedContacts = findMatchedContacts(inputType, searchInput);
        return formatContacts(matchedContacts);
    }

    public void editContact(String detailType, String oldDetail, String newDetail) {
        Contact contactToEdit = searchContact(detailType, oldDetail);
        updateContactDetail(contactToEdit, detailType, newDetail);
        checkNotDuplicate(contactToEdit);
        System.out.printf("Contact's %s has been updated to %s.\n", detailType, newDetail);
    }

    private void updateContactDetail(Contact contact, String detailType, String newDetail) {
        switch (detailType) {
            case "name": contact.setName(newDetail); break;
            case "phoneNumber": contact.setPhoneNumber(newDetail); break;
            case "emailAddress": contact.setEmailAddress(newDetail); break;
        }
    }

    public void removeContact(String detailType, String removeInput) {
        Contact contactToRemove = searchContact(detailType, removeInput);
        allContacts.remove(contactToRemove);
        System.out.println("Contact has been removed.\n");
    }

    public void deleteAllContacts() throws Exception{
        if (allContacts.isEmpty()) throw new Exception("Address book is empty.");
        allContacts.clear();
        System.out.println("All contacts deleted.\n");
    }
}
