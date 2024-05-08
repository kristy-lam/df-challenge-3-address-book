package com.dfcorp.app;

import org.junit.jupiter.api.*;

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
    }
}
