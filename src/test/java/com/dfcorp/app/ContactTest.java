package com.dfcorp.app;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class ContactTest {

    @Nested
    @DisplayName("Contact Constructor Tests")
    class ContactTests {

        @Test
        @DisplayName("1.1 Constructor sets values of name, phone number and email address when valid")
        public void testContactIsSetByConstructor() {
            // Arrange
            String testName = "Aidan Adams";
            String testPhoneNumber = "01234567890";
            String testEmailAddress = "aidanadams@abc.com";
            // Act
            Contact testContact = new Contact(testName, testPhoneNumber, testEmailAddress);
            // Assert
            assertAll(
                    () -> assertEquals(testName, testContact.getName()),
                    () -> assertEquals(testPhoneNumber, testContact.getPhoneNumber()),
                    () -> assertEquals(testEmailAddress, testContact.getEmailAddress())
            );
        }

        @Test
        @DisplayName("1.2 A success message is printed when a contact is added")
        public void testSuccessMsgWhenContactIsAdded() {
            // Arrange
            String testName = "Aidan Adams";
            String testPhoneNumber = "01234567890";
            String testEmailAddress = "aidanadams@abc.com";
            String expected = "Contact has been added.";
            // Act
            // Reassign standard output stream of System.out
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            new Contact(testName, testPhoneNumber, testEmailAddress);
            // Assert
            assertEquals(expected, outputStream.toString().trim());
        }
    }
}
