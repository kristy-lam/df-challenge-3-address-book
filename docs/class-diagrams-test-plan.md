# Class Diagrams and Test Plan

In light of the client's requirements, six user stories have been identified. To accomplish the goals in the user stories, tests have been designed and conducted to ensure that the production code would deliver the desired outcomes. To adhere to the principle of single responsibility, the third party testing framework, Mockito, has been used in tests where two classes are involved so that tests remain independent, ensuring minimal coupling.

Please see below a UML class diagram which illustrates the properties and behaviours of the classes used. After that, the user stories listed in bullet points and the associated tests for the stories. The link to the miro board containing a Kanban board tracking the work progress is also provided here: https://miro.com/app/board/uXjVKSab-T8=/?moveToWidget=3458764588218430057&cot=14.

## UML Class Diagram

```mermaid
---
title: Address Book Challenge
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
        -validateName(name String) void
        -validatePhoneNumber(phoneNumber String) void
        -validateEmailAddress(emailAddress String) void
    }
    class AddressBook {   
        -allContacts ArrayList
        +addContact(Contact) void
        +searchContact(name) Contact
        +editContact(Contact) void
        +removeContact(Contact) void
        +viewAllContacts() ArrayList
    }
    class consoleInterface {
        
        
    }

```

## User Stories and Tests

**1. As a user, I should be able to add a contact with at least a name, phone number and email address to the address book, so that I can store the information.**
   
- [x] 1.1 Test constructor sets values of name, phone number and email address when valid
- [x] 1.2 Test a success message is printed when a contact is added
- [x] 1.3 Test constructor throws exception when name is null
- [x] 1.4 Test constructor throws exception when name is an empty
- [x] 1.5 Test constructor throws exception when phone number is null
- [ ] 1. Test constructor throws exception when phone number is empty
- [ ] 1. Test constructor throws exception when phone number is not in the correct format
- [ ] 1. Test constructor throws exception when email address is null
- [ ] 1. Test constructor throws exception when email address is empty
- [ ] 1. Test constructor throws exception when email address is not in the correct format

**2. As a user, I should be able to add a contact with unique phone number and email address to the address book, so that there will not be duplicate contacts.**

**3. As a user, I should be able to search for a contact by name and have the results displayed, so that I can retrieve the contacts I want.**

**4. As a user, I should be able to edit a contact's details, so that the details can be kept up-to-date.**

**5. As a user, I should be able to remove a contact from the address book, so that unnecessary contacts can be deleted.**

**6. As a user, I should be able to view all contacts in the address book, so that I can have an overview of the contacts I have.**

**7. As a user, I should be able to use a menu to operate the address book, so that I can navigate and use the different functions easily.**
