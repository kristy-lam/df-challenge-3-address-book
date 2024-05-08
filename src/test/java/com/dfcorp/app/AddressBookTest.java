package com.dfcorp.app;

import org.junit.jupiter.api.*;
import org.mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class AddressBookTest {

    @Nested
    @DisplayName("Address Book Add Contact Tests")
    class AddressBookAddContactTests {

        AddressBook testAddressBook;
        Contact mockedContact1;
        Contact mockedContact2;

        @BeforeEach
        public void setUp() {
            testAddressBook = new AddressBook();
            // Set up Mocked Contact 1
            mockedContact1 = Mockito.mock(Contact.class);
            String testName1 = "Aidan Adams";
            String testPhoneNumber1 = "01234567890";
            String testEmailAddress1 = "aidanadams@abc.com";
            Mockito.when(mockedContact1.getName()).thenReturn(testName1);
            Mockito.when(mockedContact1.getPhoneNumber()).thenReturn(testPhoneNumber1);
            Mockito.when(mockedContact1.getEmailAddress()).thenReturn(testEmailAddress1);
            // Set up Mocked Contact 2
            mockedContact2 = Mockito.mock(Contact.class);
            String testName2 = "Blair Bay";
            String testPhoneNumber2 = "02345678901";
            String testEmailAddress2 = "blairbay@bcd.com";
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
    }
}
