# Class Diagrams and Test Plan

In light of the client's requirements, eight user stories have been identified. To accomplish the goals in the user stories, tests have been designed and conducted to ensure that the production code would deliver the desired outcomes. To adhere to the principle of single responsibility, the third party testing framework, Mockito, has been used in tests where two classes are involved so that tests remain independent, ensuring minimal coupling.

Please see below a UML class diagram which illustrates the properties and behaviours of the classes used. After that, the user stories listed in bullet points and the associated tests for the stories. A screenshot of the Kanban board tracking the development progress is attached below. Screenshots of the previous stages are stored in the "img" folder.

![Completed Kanban board](img/img-kanban-completed.png)

## UML Class Diagram

```mermaid
---
title: DF Corp Address Book App
---
classDiagram
        
    class Contact {
        -name String
        -phoneNumber String
        -emailAddress String
        +Contact(name String, phoneNumber String, emailAddress String)
        +getName() String
        +getPhoneNumber() String
        +getEmailAddress() String
        +setName(name String) void
        +setPhoneNumber(phoneNumber String) void
        +setEmailAddress(emailAddress String) void
    }
    
    class Validator {
        +validateName(name String) void$
        +validatePhoneNumber(phoneNumber String) void$
        +validateEmailAddress(emailAddress String) void$
        +validateType(type String) void$
    }
    
    class AddressBook {   
        -allContacts ArrayList
        +getAllContacts ArrayList<Contact> (kept for testing only)
        +viewAllContacts() ArrayList<Contact>
        +addContact(contact Contact) void   
        +viewContact(inputType String, searchInput String) String
        +editContact(detailType String, oldDetail String, newDetail String) void
        +removeContact(detailType String, name String) void
        -checkDuplicatePhoneNumber(contactToBeChecked Contact) void
        -checkDuplicateEmailAddress(contactToBeChecked Contact) void
        -checkNotDuplicate(contactToBeChecked Contact) void
        -searchByCriteria(criteria String, searchInput String) Contact
        -searchContact(inputType String, searchInput String) Contact
        -matchesInputType(contact Contact, inputType String, searchInput String) boolean
        -addMatchedContacts(matchedContacts ArrayList<Contact>, inputType String, searchInput String) void
        -sortContactsByName(matchedContacts ArrayList<Contact>) void
        -searchAllContacts(inputType String, searchInput String) ArrayList<Contact>
        -formatContacts(contacts ArrayList<Contact>) String
        -updateContactDetail(contact Contact, detailType String, newDetail String) void
    }
    
    class consoleInterface {
        +getInput() String$
        +promptDeletionConfirmation() boolean$
        +mainMenu() int$        
        -mainMenuText() void$
    }

    AddressBook --> Contact
    Validator ..> consoleInterface
    AddressBook ..> consoleInterface

```

## User Stories and Tests

**1. As a user, I should be able to add a contact with at least a name, phone number and email address to the address book, so that I can store the information.**
   
- [x] 1.1 Constructor sets values of name, phone number and email address when valid
- [x] 1.2 Validator throws exception when name is null
- [x] 1.3 Validator throws exception when name is empty
- [x] 1.4 Validator throws exception when name is white space
- [x] 1.5 Validator throws exception when phone number is null
- [x] 1.6 Validator throws exception when phone number is empty
- [x] 1.7 Validator throws exception when name is white space
- [x] 1.8 Validator throws exception when phone number is not in the correct UK number format
- [x] 1.9 Validator throws exception when email address is null
- [x] 1.10 Validator throws exception when email address is empty
- [x] 1.11 Validator throws exception when name is white space
- [x] 1.12 Validator throws exception when email address is not in the correct format
- [x] 1.13 Valid contact is added to address book 
- [x] 1.14 Prints success message when a contact is created

**2. As a user, I should be able to add a contact with unique phone number and email address to the address book, so that there will not be duplicate contacts.**
- [x] 2.1 Throws exception when phone number is duplicating with another contact
- [x] 2.2 Throws exception when email address is duplicating with another contact
- [x] 2.3 Adds contact when only name is duplicating with another contact

**3. As a user, I should be able to view all contacts in the address book, so that I can have an overview of the contacts I have.**
- [x] 3.1 Prints the details of all contacts 
- [x] 3.2 Throws exception when there is no contact

**4. As a user, I should be able to search for a contact and have the results displayed, so that I can retrieve the contacts I want.**
- [x] 4.1 Prints contact when the search input matches the name of the contact
- [x] 4.2 Throws exception when no match is found
- [x] *4.3 Prints contact when the search input matches the phone number of the contact
- [x] *4.4 Prints contact when the search input matches the email address of the contact
- [x] *4.5 Throws exception when type input is null
- [x] *4.6 Throws exception when type input is empty
- [x] *4.7 Throws exception when type input is white space
- [x] *4.8 Throws exception when type input does not match any type
- [x] *4.9 Prints contact(s) even when the search input is only a partial match
- [x] *4.10 Prints matched contacts in alphabetical order 
- [x] *4.11 Prints matched contacts with special characters in alphabetical order
- [x] *4.12 Prints matched contacts in alphabetical order in a case-insensitive manner

**5. As a user, I should be able to edit a contact's details, so that the details can be kept up-to-date.**
- [x] 5.1 Edits a contact's name when new name is valid
- [x] 5.2 Edits a contact's phone number when new phone number is valid
- [x] 5.3 Edits a contact's email address when new email address is valid
- [x] 5.4 Prints success message when a contact is edited

**6. As a user, I should be able to remove a contact from the address book, so that unnecessary contacts can be deleted.**
- [x] 6.1 Removes the selected contact in the address book
- [x] 6.2 Prints success message when a contact is removed

**7. As a user, I should be able to use a console interface to operate the address book, so that I can navigate and use the different functions easily.**
- [x] 7.1 Throws exception when main menu input is empty
- [x] 7.2 Throws exception when main menu input is not a number between 0 and 6

***8. As a user, I should be able to delete all contacts at once after a confirmation, so that I can reset the address book easily.**
- [x] *8.1 Throws exception when address book is empty
- [x] *8.2 Interface prompts user to confirm deletion
- [x] *8.3 Deletes all contacts
- [x] *8.4 Prints success message when all contacts are deleted

_Note: User story and tests starting with * are designed for the additional tasks._