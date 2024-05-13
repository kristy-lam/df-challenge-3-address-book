package com.dfcorp.app;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ContactTest {

    @Nested
    @DisplayName("Contact Constructor Tests")
    class ContactConstructorTests {
        String testName;
        String testPhoneNumber;
        String testEmailAddress;

        @BeforeEach
        public void setUp() {
            testName = "Aidan Adams";
            testPhoneNumber = "01234567890";
            testEmailAddress = "aidanadams@abc.com";
        }

        @AfterEach
        public void tearDown() {
            testName = null;
            testPhoneNumber = null;
            testEmailAddress = null;
        }

        @Test
        @DisplayName("1.1 Constructor sets values of name, phone number and email address when valid")
        public void testContactIsSetByConstructor() {
            // Arrange - Refactored to beforeEach
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
