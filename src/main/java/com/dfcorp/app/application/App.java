package com.dfcorp.app.application;

import com.dfcorp.app.*;

import static com.dfcorp.app.Validator.*;

public class App {
    public static void main(String[] args) throws Exception {

        AddressBook addressBook = new AddressBook();

        while (true) {
            int userInput = ConsoleInterface.mainMenu();
            switch (userInput) {
                case 0:
                    System.out.println("Goodbye!");
                    return;
                case 1:
                    addContact(addressBook);
                    break;
                case 2:
                    editContact(addressBook);
                    break;
                case 3:
                    removeContact(addressBook);
                    break;
                case 4:
                    viewContact(addressBook);
                    break;
                case 5:
                    viewAllContacts(addressBook);
                    break;
            }
        }
    }

    private static void addContact(AddressBook addressBook) {
        System.out.println("Enter contact name: ");
        String contactName = ConsoleInterface.getInput();
        validateString(contactName);

        System.out.println("Enter contact phone number (in 10 or 11 digits): ");
        String contactPhoneNumber = ConsoleInterface.getInput();
        validatePhoneNumber(contactPhoneNumber);

        System.out.println("Enter contact email address: ");
        String contactEmailAddress = ConsoleInterface.getInput();
        validateEmailAddress(contactEmailAddress);

        Contact contact = new Contact(contactName, contactPhoneNumber, contactEmailAddress);
        addressBook.addContact(contact);
    }

    private static void editContact(AddressBook addressBook) {

        System.out.println("Enter type of contact you want to search (name, phoneNumber, or emailAddress): ");
        String detailType = ConsoleInterface.getInput();
        validateType(detailType);

        System.out.println("Enter old contact detail: ");
        String oldDetail = ConsoleInterface.getInput();

        System.out.println("Enter new contact detail: ");
        String newDetail = ConsoleInterface.getInput();

        if (detailType.equals("name")) validateString(newDetail);
        if (detailType.equals("phoneNumber")) validatePhoneNumber(newDetail);
        if (detailType.equals("emailAddress")) validateEmailAddress(newDetail);

        addressBook.editContact(detailType, oldDetail, newDetail);
    }

    private static void removeContact(AddressBook addressBook) {
        System.out.println("Enter name of the contact you want to remove: ");
        String nameInput = ConsoleInterface.getInput();
        addressBook.removeContact("name", nameInput);
    }

    private static void viewContact(AddressBook addressBook) throws Exception {
        System.out.println("Enter type of contact you want to search (name, phoneNumber, or emailAddress): ");
        String detailType = ConsoleInterface.getInput();
        validateType(detailType);
        System.out.println("Enter contact detail you are searching for: ");
        String detailInput = ConsoleInterface.getInput();
        validateString(detailType);
        System.out.println(addressBook.viewContact(detailType, detailInput));
    }

    private static void viewAllContacts(AddressBook addressBook) throws Exception {
        System.out.println(addressBook.viewAllContacts());
    }

}
