package com.dfcorp.app;

import org.junit.jupiter.api.*;
import org.mockito.*;
import java.io.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AddressBookTest {

    AddressBook testAddressBook;
    Contact mockedContact1;
    Contact mockedContact2;
    String testName1 = "Aidan Adams";
    String testPhoneNumber1 = "01234567890";
    String testEmailAddress1 = "aidanadams@abc.com";
    String testName2 = "Blair Bay";
    String testPhoneNumber2 = "02345678901";
    String testEmailAddress2 = "blairbay@bcd.com";

    @BeforeEach
    public void setUp() {
        testAddressBook = new AddressBook();
        // Set up Mocked Contact 1
        mockedContact1 = Mockito.mock(Contact.class);
        when(mockedContact1.getName()).thenReturn(testName1);
        when(mockedContact1.getPhoneNumber()).thenReturn(testPhoneNumber1);
        when(mockedContact1.getEmailAddress()).thenReturn(testEmailAddress1);
        // Set up Mocked Contact 2
        mockedContact2 = Mockito.mock(Contact.class);
        when(mockedContact2.getName()).thenReturn(testName2);
        when(mockedContact2.getPhoneNumber()).thenReturn(testPhoneNumber2);
        when(mockedContact2.getEmailAddress()).thenReturn(testEmailAddress2);
    }

    @AfterEach
    public void tearDown() {
        testAddressBook = null;
        mockedContact1 = null;
        mockedContact2 = null;
    }

    @Nested
    @DisplayName("Address Book Add Contact Tests")
    class AddressBookAddContactTests {

        @Test
        @DisplayName("1.13 Valid contact is added to address book")
        public void testValidContactIsAddedToAddressBook() {
            // Arrange
            // Act
            testAddressBook.addContact(mockedContact1);
            // Assert
            assertEquals(mockedContact1, testAddressBook.getAllContacts().get(0));
        }

        @Test
        @DisplayName("1.14 Prints success message when a contact is added")
        public void testSuccessMsgWhenContactIsAdded() {
            // Arrange
            String expected = "Contact has been added.";
            // Act
            // Reassign standard output stream of System.out as an instance of PrintStream
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            testAddressBook.addContact(mockedContact1);
            // Assert
            assertEquals(expected, outputStream.toString().trim());
        }

        @Test
        @DisplayName("2.1 Throws exception when phone number is duplicating with another contact")
        public void testExceptionThrownWhenPhoneNumberIsDuplicatingWithAnotherContact() {
            // Arrange
            testAddressBook.addContact(mockedContact1);
            // Act
            // Same phone number as Mocked Contact 1
            when(mockedContact2.getPhoneNumber()).thenReturn(testPhoneNumber1);
            // Assert
            assertThrows(IllegalArgumentException.class, () -> testAddressBook.addContact(mockedContact2));
        }

        @Test
        @DisplayName("2.2 Throws exception when email address is duplicating with another contact")
        public void testExceptionThrownWhenEmailAddressIsDuplicatingWithAnotherContact() {
            // Arrange
            testAddressBook.addContact(mockedContact1);
            // Act
            // Same email address as Mocked Contact 1
            when(mockedContact2.getEmailAddress()).thenReturn(testEmailAddress1);
            // Assert
            assertThrows(IllegalArgumentException.class, () -> testAddressBook.addContact(mockedContact2));
        }

        @Test
        @DisplayName("2.3 Adds contact when only name is duplicating with another contact")
        public void testValidContactWithDuplicateNameIsAddedToAddressBook() {
            // Arrange
            testAddressBook.addContact(mockedContact1);
            // Act
            // Same name as Mocked Contact 1
            when(mockedContact2.getName()).thenReturn(testName1);
            testAddressBook.addContact(mockedContact2);
            // Assert
            assertEquals(2, testAddressBook.getAllContacts().size());
        }
    }

    @Nested
    @DisplayName("Address Book View All Contacts Tests")
    class AddressBookViewAllContactsTests {

        @Test
        @DisplayName("3.1 Prints the details of all contacts")
        public void testDetailsOfAllContactsPrinted() throws Exception {
            // Arrange
            String expectedStr1 = "Contact { name=Aidan Adams, phoneNumber=01234567890, " +
                    "emailAddress=aidanadams@abc.com }\n";
            String expectedStr2 = "Contact { name=Blair Bay, phoneNumber=02345678901, " +
                    "emailAddress=blairbay@bcd.com }\n";
            ArrayList<String> expected = new ArrayList<>(List.of(expectedStr1, expectedStr2));
            // Define action of mock contacts when their toString method is called
            // as in the viewAllContacts method being tested
            when(mockedContact1.toString()).thenReturn(expectedStr1);
            when(mockedContact2.toString()).thenReturn(expectedStr2);
            testAddressBook.addContact(mockedContact1);
            testAddressBook.addContact(mockedContact2);
            // Act
            ArrayList<String> actual = testAddressBook.viewAllContacts();
            // Assert
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("3.2 Throws exception when there is no contact")
        public void testExceptionWhenNoContact() {
            // Arrange
            // Act - testAddressBook is empty by default
            // Assert
            assertThrows(Exception.class, () -> testAddressBook.viewAllContacts());
        }
    }

    @Nested
    @DisplayName("Address Book View Contact Tests")
    class AddressBookViewContactTests {

        @BeforeEach
        public void setUp() {
            testAddressBook.addContact(mockedContact1);
            String mockedContact1ToString = "Contact { name=Aidan Adams, phoneNumber=01234567890, " +
                    "emailAddress=aidanadams@abc.com }\n";
            // Define action of mock contact when its toString method is called
            // as in the displayContact method being tested
            when(mockedContact1.toString()).thenReturn(mockedContact1ToString);
        }

        @AfterEach
        public void tearDown() {
            testAddressBook = null;
            mockedContact1 = null;
        }

        @Test
        @DisplayName("4.1 Prints contact when the search input matches the name of the contact")
        public void testPrintContactWhenSearchInputByNameMatches() throws Exception {
            // Arrange
            String expected = "Matched Contact(s):\nContact { name=Aidan Adams, phoneNumber=01234567890, " +
                    "emailAddress=aidanadams@abc.com }\n";
            // Act
            String actual = testAddressBook.viewContact("name", "Aidan Adams");
            // Assert
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("4.2 Throws exception when no match is found")
        public void testExceptionWhenNoMatch() {
            // Arrange
            // Act
            // Assert
            assertThrows(Exception.class, () -> testAddressBook.viewContact("name", "Blair Bay"));
        }

        @Test
        @DisplayName("4.3 Prints contact when the search input matches the phone number of the contact")
        public void testPrintContactWhenSearchInputByPhoneNumberMatches() throws Exception {
            // Arrange
            String expected = "Matched Contact(s):\nContact { name=Aidan Adams, phoneNumber=01234567890, " +
                    "emailAddress=aidanadams@abc.com }\n";
            // Act
            String actual = testAddressBook.viewContact("phoneNumber", "01234567890");
            // Assert
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("4.4 Prints contact when the search input matches the email address of the contact")
        public void testPrintContactWhenSearchInputByEmailAddressMatches() throws Exception {
            // Arrange
            String expected = "Matched Contact(s):\nContact { name=Aidan Adams, phoneNumber=01234567890, " +
                    "emailAddress=aidanadams@abc.com }\n";
            // Act
            String actual = testAddressBook.viewContact("emailAddress", "aidanadams@abc.com");
            // Assert
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("4.9 Prints contact(s) even when the search input is a only partial match")
        public void testPrintsContactsWhenPartialMatch() throws Exception {
            // Arrange
            String expectedStr1 = "Contact { name=Aidan Adams, phoneNumber=01234567890, " +
                    "emailAddress=aidanadams@abc.com }\n";
            String expectedStr2 = "Contact { name=Aidan, phoneNumber=09876543210, " +
                    "emailAddress=hello@gmail.com }\n";

            Contact mockedContact3 = Mockito.mock(Contact.class);
            when(mockedContact3.getName()).thenReturn("Aidan");
            when(mockedContact3.getPhoneNumber()).thenReturn("09876543210");
            when(mockedContact3.getEmailAddress()).thenReturn("hello@gmail.com");
            when(mockedContact3.toString()).thenReturn(expectedStr2);
            testAddressBook.addContact(mockedContact3);

            String expected = "Matched Contact(s):\n" + expectedStr2 + expectedStr1;
            // Act
            String actual = testAddressBook.viewContact("name", "Aidan");
            // Assert
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("4.10 Prints matched contacts in alphabetical order")
        public void testPrintsContactsInAlphabeticalOrder() throws Exception {
            // Arrange
            String expectedStrAidan = "Contact { name=Aidan Adams, phoneNumber=01234567890, " +
                    "emailAddress=aidanadams@abc.com }\n";
            String expectedStrAaron = "Contact { name=Aaron Carter, phoneNumber=09876543210, " +
                    "emailAddress=aaron@abc.com }\n";

            Contact mockedContact3 = Mockito.mock(Contact.class);
            when(mockedContact3.getName()).thenReturn("Aaron Carter");
            when(mockedContact3.getPhoneNumber()).thenReturn("09876543210");
            when(mockedContact3.getEmailAddress()).thenReturn("emailAddress=aaron@abc.com");
            when(mockedContact3.toString()).thenReturn(expectedStrAaron);
            testAddressBook.addContact(mockedContact3);

            String expected = "Matched Contact(s):\n" + expectedStrAaron + expectedStrAidan;
            // Act
            String actual = testAddressBook.viewContact("emailAddress", "abc.com");
            // Assert
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("4.11 Prints matched contacts with special characters in alphabetical order")
        public void testPrintsContactsWithSpecialCharInAlphabeticalOrder() throws Exception {
            // Arrange
            String expectedStrAidan = "Contact { name=Aidan Adams, phoneNumber=01234567890, " +
                    "emailAddress=aidanadams@abc.com }\n";
            String expectedStrAaron = "Contact { name=Aaron Carter?, phoneNumber=09876543210, " +
                    "emailAddress=aaron@abc.com }\n";

            Contact mockedContact3 = Mockito.mock(Contact.class);
            when(mockedContact3.getName()).thenReturn("Aaron Carter?");
            when(mockedContact3.getPhoneNumber()).thenReturn("09876543210");
            when(mockedContact3.getEmailAddress()).thenReturn("emailAddress=aaron@abc.com");
            when(mockedContact3.toString()).thenReturn(expectedStrAaron);
            testAddressBook.addContact(mockedContact3);

            String expected = "Matched Contact(s):\n" + expectedStrAaron + expectedStrAidan;
            // Act
            String actual = testAddressBook.viewContact("emailAddress", "abc.com");
            // Assert
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("4.12 Prints matched contacts in alphabetical order in a case-insensitive manner")
        public void testPrintsContactsInAlphabeticalOrderInCaseInsensitiveManner() throws Exception {
            // Arrange
            String expectedStrAidan = "Contact { name=Aidan Adams, phoneNumber=01234567890, " +
                    "emailAddress=aidanadams@abc.com }\n";
            String expectedStrAaron = "Contact { name=aaron carter, phoneNumber=09876543210, " +
                    "emailAddress=aaron@abc.com }\n";

            Contact mockedContact3 = Mockito.mock(Contact.class);
            when(mockedContact3.getName()).thenReturn("aaron carter");
            when(mockedContact3.getPhoneNumber()).thenReturn("09876543210");
            when(mockedContact3.getEmailAddress()).thenReturn("emailAddress=aaron@abc.com");
            when(mockedContact3.toString()).thenReturn(expectedStrAaron);
            testAddressBook.addContact(mockedContact3);

            String expected = "Matched Contact(s):\n" + expectedStrAaron + expectedStrAidan;
            // Act
            String actual = testAddressBook.viewContact("emailAddress", "abc.com");
            // Assert
            assertEquals(expected, actual);
        }
    }

    @Nested
    @DisplayName("Address Book Edit Contact Tests")
    class AddressBookEditContactTests {

        @BeforeEach
        public void setUp() {
            testAddressBook.addContact(mockedContact1);
        }

        @AfterEach
        public void tearDown() {
            testAddressBook = null;
        }

        @Test
        @DisplayName("5.1 Edits a contact's name when new name is valid")
        public void testEditsContactNameWhenValid() {
            // Arrange
            String expected = "Chris Cart";
            // Act
            testAddressBook.editContact("name", "Aidan Adams", expected);
            // Assert
            // Check if the name setter within Mocked Contact 1 is called
            // as in the editName method being tested
            verify(mockedContact1, times(1)).setName(expected);
        }

        @Test
        @DisplayName("5.2 Edits a contact's phone number when new phone number is valid")
        public void testEditsContactPhoneNumberWhenValid() {
            // Arrange
            String expected = "09876543210";
            // Act
            testAddressBook.editContact("phoneNumber", "01234567890", expected);
            // Assert
            // Check if the name setter within Mocked Contact 1 is called
            // as in the editName method being tested
            verify(mockedContact1, times(1)).setPhoneNumber(expected);
        }

        @Test
        @DisplayName("5.3 Edits a contact's email address when new email address is valid")
        public void testEditsContactEmailAddressWhenValid() {
            // Arrange
            String expected = "aidan-adams@abc.com";
            // Act
            testAddressBook.editContact("emailAddress", "aidanadams@abc.com", expected);
            // Assert
            // Check if the name setter within Mocked Contact 1 is called
            // as in the editName method being tested
            verify(mockedContact1, times(1)).setEmailAddress(expected);
        }

        @Test
        @DisplayName("5.4 Prints success message when a contact is edited")
        public void testSuccessMsgWhenContactIsEdited() {
            // Arrange
            String expected = "Contact's name has been updated to Chris Cart.";
            // Act
            // Reassign standard output stream of System.out as an instance of PrintStream
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            testAddressBook.editContact("name", "Aidan Adams", "Chris Cart");
            // Assert
            assertEquals(expected, outputStream.toString().trim());
        }
    }

    @Nested
    @DisplayName("Address Book Remove Contact Tests")
    class AddressBookRemoveContactTests {

        @BeforeEach
        public void setUp() {
            testAddressBook.addContact(mockedContact1);;
        }

        @AfterEach
        public void tearDown() {
            testAddressBook = null;
        }

        @Test
        @DisplayName("6.1 Removes the selected contact in the address book")
        public void testRemovesContact() {
            // Arrange
            // Act
            testAddressBook.removeContact("name", "Aidan Adams");
            // Assert
            assertFalse(testAddressBook.getAllContacts().contains(mockedContact1));
        }

        @Test
        @DisplayName("6.2 Prints success message when a contact is removed")
        public void testSuccessMsgWhenContactIsRemoved() {
            // Arrange
            String expected = "Contact has been removed.";
            // Act
            // Reassign standard output stream of System.out as an instance of PrintStream
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            testAddressBook.removeContact("name", "Aidan Adams");
            // Assert
            assertEquals(expected, outputStream.toString().trim());
        }
    }

    @Nested
    @DisplayName("Address Book Delete All Contacts Tests")
    class AddressBookDeleteAllContactsTests {

        @BeforeEach
        public void setUp() {
            testAddressBook.addContact(mockedContact1);
            testAddressBook.addContact(mockedContact2);
        }

        @AfterEach
        public void tearDown() {
            testAddressBook = null;
        }

        @Test
        @DisplayName("8.1 Throws exception when address book is empty")
        public void testExceptionWhenAddressBookIsEmpty() {
            // Arrange
            testAddressBook = null;
            // Act
            // Assert
            assertThrows(Exception.class, () -> testAddressBook.deleteAllContacts());
        }

        @Test
        @DisplayName("8.3 Deletes all contacts")
        public void testDeleteAllContacts() throws Exception {
            // Arrange
            // Act
            testAddressBook.deleteAllContacts();
            // Assert
            assertEquals(0, testAddressBook.getAllContacts().size());
        }

    }
}
