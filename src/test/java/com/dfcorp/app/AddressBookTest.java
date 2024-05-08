package com.dfcorp.app;

import org.junit.jupiter.api.*;
import org.mockito.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class AddressBookTest {

    @Nested
    @DisplayName("Address Book Add Contact Tests")
    class AddressBookAddContactTests {

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
            Mockito.when(mockedContact1.getName()).thenReturn(testName1);
            Mockito.when(mockedContact1.getPhoneNumber()).thenReturn(testPhoneNumber1);
            Mockito.when(mockedContact1.getEmailAddress()).thenReturn(testEmailAddress1);
            // Set up Mocked Contact 2
            mockedContact2 = Mockito.mock(Contact.class);
            Mockito.when(mockedContact2.getName()).thenReturn(testName2);
            Mockito.when(mockedContact2.getPhoneNumber()).thenReturn(testPhoneNumber2);
            Mockito.when(mockedContact2.getEmailAddress()).thenReturn(testEmailAddress2);
        }

        @AfterEach
        public void tearDown() {
            testAddressBook = null;
            mockedContact1 = null;
            mockedContact2 = null;
        }

        @Test
        @DisplayName("1.10 Valid contact is added to address book")
        public void testValidContactIsAddedToAddressBook() {
            // Arrange
            // Act
            testAddressBook.addContact(mockedContact1);
            // Assert
            assertEquals(mockedContact1, testAddressBook.getAllContacts().get(0));
        }

        @Test
        @DisplayName("1.11 A success message is printed when a contact is added")
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
            Mockito.when(mockedContact2.getPhoneNumber()).thenReturn(testPhoneNumber1);
            // Assert
            assertThrows(IllegalArgumentException.class, () -> testAddressBook.addContact(mockedContact2));
        }
    }
}
