package com.dfcorp.app;

import org.junit.jupiter.api.*;
import org.mockito.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

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
}
