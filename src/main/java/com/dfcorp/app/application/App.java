package com.dfcorp.app.application;

import com.dfcorp.app.*;

public class App {

    public static void main(String[] args) throws Exception {

        AddressBook addressBook = new AddressBook();

        // Create and add demo contacts
        Contact demoContact1 = new Contact("Aiden Adams", "01234567890", "aidan@dfcorp.com");
        Contact demoContact2 = new Contact("Blair Bay", "02345678901", "blair@dfcorp.com");
        Contact demoContact3 = new Contact("Chris Clay", "03456789012", "chris@abc.com");
        Contact demoContact4 = new Contact("Dany Day?", "04567890123", "dany@abc.com");
        Contact demoContact5 = new Contact("Dany Dain", "04567890120", "dany@bcd.com");
        Contact demoContact6 = new Contact("Ellen", "05678901234", "ellen@gmail.com");
        addressBook.addContact(demoContact1);
        addressBook.addContact(demoContact2);
        addressBook.addContact(demoContact3);
        addressBook.addContact(demoContact4);
        addressBook.addContact(demoContact5);
        addressBook.addContact(demoContact6);

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
                    viewContacts(addressBook);
                    break;
                case 5:
                    viewAllContacts(addressBook);
                    break;
                case 6:
                    deleteAllContacts(addressBook);
                    break;
            }
        }
    }


    // Code below combine methods from all classes in the app

    private static void addContact(AddressBook addressBook) {
        System.out.println("Enter contact name: ");
        String contactName = ConsoleInterface.getInput();
        Validator.validateString(contactName);

        System.out.println("Enter contact phone number (in 10 or 11 digits): ");
        String contactPhoneNumber = ConsoleInterface.getInput();
        Validator.validatePhoneNumber(contactPhoneNumber);

        System.out.println("Enter contact email address: ");
        String contactEmailAddress = ConsoleInterface.getInput();
        Validator.validateEmailAddress(contactEmailAddress);

        Contact contact = new Contact(contactName, contactPhoneNumber, contactEmailAddress);
        addressBook.addContact(contact);
    }

    private static void editContact(AddressBook addressBook) {
        System.out.println("Enter type of contact you want to search (name, phoneNumber, or emailAddress): ");
        String detailType = ConsoleInterface.getInput();
        Validator.validateType(detailType);

        System.out.println("Enter old contact detail: ");
        String oldDetail = ConsoleInterface.getInput();

        System.out.println("Enter new contact detail: ");
        String newDetail = ConsoleInterface.getInput();

        if (detailType.equals("name")) Validator.validateString(newDetail);
        if (detailType.equals("phoneNumber")) Validator.validatePhoneNumber(newDetail);
        if (detailType.equals("emailAddress")) Validator.validateEmailAddress(newDetail);

        addressBook.editContact(detailType, oldDetail, newDetail);
    }

    private static void removeContact(AddressBook addressBook) {
        System.out.println("Enter name of the contact you want to remove: ");
        String nameInput = ConsoleInterface.getInput();
        addressBook.removeContact("name", nameInput);
    }

    private static void viewContacts(AddressBook addressBook) throws Exception {
        System.out.println("Enter type of contact you want to search (name, phoneNumber, or emailAddress): ");
        String detailType = ConsoleInterface.getInput();
        Validator.validateType(detailType);
        System.out.println("Enter contact detail you are searching for: ");
        String detailInput = ConsoleInterface.getInput();
        Validator.validateString(detailType);
        System.out.println(addressBook.viewContact(detailType, detailInput));
    }

    private static void viewAllContacts(AddressBook addressBook) throws Exception {
        System.out.println(addressBook.viewAllContacts());
    }

    private static void deleteAllContacts(AddressBook addressBook) throws Exception {
        boolean userInput = ConsoleInterface.promptDeletionConfirmation();
        if (userInput) {
            addressBook.deleteAllContacts();
        } else ConsoleInterface.mainMenu();
    }
}
