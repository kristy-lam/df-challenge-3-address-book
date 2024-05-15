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
        public void testPrintContactWhenSearchInputByNameMatches() {
            // Arrange
            String expected = "Contact { name=Aidan Adams, phoneNumber=01234567890, " +
                    "emailAddress=aidanadams@abc.com }\n";
            // Act
            String actual = testAddressBook.viewContact("name", "Aidan Adams");
            // Assert
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("4.2 Throws exception when search input is null")
        public void testExceptionWhenSearchInputByNameIsNull() {
            // Arrange
            // Act
            // Assert
            assertThrows(IllegalArgumentException.class, () -> testAddressBook.viewContact("name", null));
        }

        @Test
        @DisplayName("4.3 Throws exception when search input is empty")
        public void testExceptionWhenSearchInputByNameIsEmpty() {
            // Arrange
            // Act
            // Assert
            assertThrows(IllegalArgumentException.class, () -> testAddressBook.viewContact("name", ""));
        }

        @Test
        @DisplayName("4.4 Throws exception when search input is white space")
        public void testExceptionWhenSearchInputByNameIsWhiteSpace() {
            // Arrange
            // Act
            // Assert
            assertThrows(IllegalArgumentException.class, () -> testAddressBook.viewContact("name", "  "));
        }

        @Test
        @DisplayName("4.5 Throws exception when search input does not match any name")
        public void testExceptionWhenSearchInputByNameDoesNotMatch() {
            // Arrange
            // Act
            // Assert
            assertThrows(IllegalArgumentException.class, () -> testAddressBook.viewContact("name", "Blair Bay"));
        }

        @Test
        @DisplayName("4.6 Prints contact when the search input matches the phone number of the contact")
        public void testPrintContactWhenSearchInputByPhoneNumberMatches() {
            // Arrange
            String expected = "Contact { name=Aidan Adams, phoneNumber=01234567890, " +
                    "emailAddress=aidanadams@abc.com }\n";
            // Act
            String actual = testAddressBook.viewContact("phoneNumber", "01234567890");
            // Assert
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("4.7 Prints contact when the search input matches the email address of the contact")
        public void testPrintContactWhenSearchInputByEmailAddressMatches() {
            // Arrange
            String expected = "Contact { name=Aidan Adams, phoneNumber=01234567890, " +
                    "emailAddress=aidanadams@abc.com }\n";
            // Act
            String actual = testAddressBook.viewContact("emailAddress", "aidanadams@abc.com");
            // Assert
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("4.8 Throws exception when the type input is null")
        public void testExceptionWhenTypeInputIsNull() {
            // Arrange
            // Act
            // Assert
            assertThrows(IllegalArgumentException.class, () -> testAddressBook.viewContact(null, "Aidan Adams"));
        }

        @Test
        @DisplayName("4.9 Throws exception when the type input is empty")
        public void testExceptionWhenTypeInputIsEmpty() {
            // Arrange
            // Act
            // Assert
            assertThrows(IllegalArgumentException.class, () -> testAddressBook.viewContact("", "Aidan Adams"));
        }

        @Test
        @DisplayName("4.10 Throws exception when the type input is white space")
        public void testExceptionWhenTypeInputIsWhiteSpace() {
            // Arrange
            // Act
            // Assert
            assertThrows(IllegalArgumentException.class, () -> testAddressBook.viewContact("  ", "Aidan Adams"));
        }

        @Test
        @DisplayName("4.11 Throws exception when the type input does not match any type")
        public void testExceptionWhenTypeInputDoesNotMatch() {
            // Arrange
            // Act
            // Assert
            assertThrows(IllegalArgumentException.class, () -> testAddressBook.viewContact("contact name", "Aidan Adams"));
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

}
