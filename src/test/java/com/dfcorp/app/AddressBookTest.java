package com.dfcorp.app;

import org.junit.jupiter.api.*;
import org.mockito.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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
        @DisplayName("1.14 A success message is printed when a contact is added")
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
    @DisplayName("Address Book View Contacts Tests")
    class AddressBookViewContactsTests {

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
    @DisplayName("Address Book Search Contact Tests")
    class AddressBookSearchContactTests {

        @BeforeEach
        public void setUp() {
            testAddressBook.addContact(mockedContact1);
            String mockedContact1ToString = "Contact { name=Aidan Adams, phoneNumber=01234567890, " +
                    "emailAddress=aidanadams@abc.com }\n";
            // Define action of mock contact when its toString method is called
            // as in the searchContactByName method being tested
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
            String actual = testAddressBook.searchContactByName("Aidan Adams");
            // Assert
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("4.2 Throws exception when search input is null")
        public void testExceptionWhenSearchInputByNameIsNull() {
            // Arrange
            // Act
            // Assert
            assertThrows(IllegalArgumentException.class, () -> testAddressBook.searchContactByName(null));
        }

        @Test
        @DisplayName("4.3 Throws exception when search input is empty")
        public void testExceptionWhenSearchInputByNameIsEmpty() {
            // Arrange
            // Act
            // Assert
            assertThrows(IllegalArgumentException.class, () -> testAddressBook.searchContactByName(""));
        }

        @Test
        @DisplayName("4.4 Throws exception when search input is white space")
        public void testExceptionWhenSearchInputByNameIsWhiteSpace() {
            // Arrange
            // Act
            // Assert
            assertThrows(IllegalArgumentException.class, () -> testAddressBook.searchContactByName("  "));
        }

        @Test
        @DisplayName("4.5 Throws exception when search input does not match any name")
        public void testExceptionWhenSearchInputByNameDoesNotMatch() {
            // Arrange
            // Act
            // Assert
            assertThrows(IllegalArgumentException.class, () -> testAddressBook.searchContactByName("Blair Bay"));
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
        @DisplayName("5.2 Throws exception when new name is null")
        public void testExceptionWhenNewNameIsNull() {
            // Arrange
            // Act
            // Assert
            assertThrows(IllegalArgumentException.class, () -> testAddressBook.editContact(
                    "name", "Aidan Adams", null));
        }

        @Test
        @DisplayName("5.3 Throws exception when new name is empty")
        public void testExceptionWhenNewNameIsEmpty() {
            // Arrange
            // Act
            // Assert
            assertThrows(IllegalArgumentException.class, () -> testAddressBook.editContact(
                    "name", "Aidan Adams", ""));
        }

        @Test
        @DisplayName("5.4 Throws exception when new name is white space")
        public void testExceptionWhenNewNameIsWhiteSpace() {
            // Arrange
            // Act
            // Assert
            assertThrows(IllegalArgumentException.class, () -> testAddressBook.editContact(
                    "name", "Aidan Adams", "  "));
        }

    }
}
