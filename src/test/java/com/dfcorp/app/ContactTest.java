package com.dfcorp.app;

import org.junit.jupiter.api.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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

        @Test
        @DisplayName("1.3 Constructor throws exception when name is an empty")
        public void testExceptionThrownWhenNameIsEmpty() {
            // Arrange
            String testName = "";
            // Act
            // Assert
            assertThrows(IllegalArgumentException.class, () -> new Contact(testName, testPhoneNumber, testEmailAddress));
        }

        @Test
        @DisplayName("1.4 Constructor throws exception when phone number is null")
        public void testExceptionThrownWhenPhoneNumberIsNull() {
            // Arrange
            String testPhoneNumber = null;
            // Act
            // Assert
            assertThrows(IllegalArgumentException.class, () -> new Contact(testName, testPhoneNumber, testEmailAddress));
        }

        @Test
        @DisplayName("1.5 Constructor throws exception when phone number is empty")
        public void testExceptionThrownWhenPhoneNumberIsEmpty() {
            // Arrange
            String testPhoneNumber = "";
            // Act
            // Assert
            assertThrows(IllegalArgumentException.class, () -> new Contact(testName, testPhoneNumber, testEmailAddress));
        }

        @Test
        @DisplayName("1.6 Constructor throws exception when phone number is not in the correct UK number format")
        public void testExceptionThrownWhenPhoneNumberIsNotInCorrectUKFormat() {
            // Arrange
            String testPhoneNumber = "01234567";
            // Act
            // Assert
            assertThrows(IllegalArgumentException.class, () -> new Contact(testName, testPhoneNumber, testEmailAddress));
        }

        @Test
        @DisplayName("1.7 Constructor throws exception when email address is null")
        public void testExceptionThrownWhenEmailAddressIsNull() {
            // Arrange
            String testEmailAddress = null;
            // Act
            // Assert
            assertThrows(IllegalArgumentException.class, () -> new Contact(testName, testPhoneNumber, testEmailAddress));
        }

        @Test
        @DisplayName("1.8 Constructor throws exception when email address is empty")
        public void testExceptionThrownWhenEmailAddressIsEmpty() {
            // Arrange
            String testEmailAddress = "";
            // Act
            // Assert
            assertThrows(IllegalArgumentException.class, () -> new Contact(testName, testPhoneNumber, testEmailAddress));
        }

        @Test
        @DisplayName("1.9 Test constructor throws exception when email address is not in the correct format")
        public void testExceptionThrownWhenEmailAddressIsNotInCorrectFormat() {
            // Arrange
            String testEmailAddress = "hello@world.";
            // Act
            // Assert
            assertThrows(IllegalArgumentException.class, () -> new Contact(testName, testPhoneNumber, testEmailAddress));
        }
    }
}
