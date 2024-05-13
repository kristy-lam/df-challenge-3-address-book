package com.dfcorp.app;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidatorTest {

    @Nested
    @DisplayName("Validate String Tests")
    class ValidateStringTests {

        @Test
        @DisplayName("1.3 Validator throws exception when name is empty")
        public void testExceptionThrownWhenNameIsEmpty() {
            // Arrange
            String invalidTestName = "";
            // Act
            // Assert
            assertThrows(IllegalArgumentException.class, () -> Validator.validateString(invalidTestName));
        }

        @Test
        @DisplayName("1.4 Validator throws exception when name is white space")
        public void testExceptionThrownWhenNameIsWhiteSpace() {
            // Arrange
            String invalidTestName = "   ";
            // Act
            // Assert
            assertThrows(IllegalArgumentException.class, () -> Validator.validateString(invalidTestName));
        }
    }

    @Nested
    @DisplayName("Validate Phone Number Tests")
    class ValidatePhoneNumberTests {

        @Test
        @DisplayName("1.5 Validator throws exception when phone number is null")
        public void testExceptionThrownWhenPhoneNumberIsNull() {
            // Arrange
            String invalidTestPhoneNumber = null;
            // Act
            // Assert
            assertThrows(IllegalArgumentException.class, () -> Validator.validatePhoneNumber(invalidTestPhoneNumber));
        }

        @Test
        @DisplayName("1.6 Validator throws exception when phone number is empty")
        public void testExceptionThrownWhenPhoneNumberIsEmpty() {
            // Arrange
            String invalidTestPhoneNumber = "";
            // Act
            // Assert
            assertThrows(IllegalArgumentException.class, () -> Validator.validatePhoneNumber(invalidTestPhoneNumber));
        }

        @Test
        @DisplayName("1.7 Validator throws exception when phone number is white space")
        public void testExceptionThrownWhenPhoneNumberIsWhiteSpace() {
            // Arrange
            String invalidTestPhoneNumber = "   ";
            // Act
            // Assert
            assertThrows(IllegalArgumentException.class, () -> Validator.validatePhoneNumber(invalidTestPhoneNumber));
        }

        @Test
        @DisplayName("1.8 Validator throws exception when phone number is not in the correct UK number format")
        public void testExceptionThrownWhenPhoneNumberIsNotInCorrectUKFormat() {
            // Arrange
            String invalidTestPhoneNumber = "01234567";
            // Act
            // Assert
            assertThrows(IllegalArgumentException.class, () -> Validator.validatePhoneNumber(invalidTestPhoneNumber));
        }
    }

    @Nested
    @DisplayName("Validate Email Address Tests")
    class ValidateEmailAddressTests {

        @Test
        @DisplayName("1.9 Validator throws exception when email address is null")
        public void testExceptionThrownWhenEmailAddressIsNull() {
            // Arrange
            String invalidTestEmailAddress = null;
            // Act
            // Assert
            assertThrows(IllegalArgumentException.class, () -> Validator.validateEmailAddress(invalidTestEmailAddress));
        }

        @Test
        @DisplayName("1.10 Validator throws exception when email address is empty")
        public void testExceptionThrownWhenEmailAddressIsEmpty() {
            // Arrange
            String invalidTestEmailAddress = "";
            // Act
            // Assert
            assertThrows(IllegalArgumentException.class, () -> Validator.validateEmailAddress(invalidTestEmailAddress));
        }

        @Test
        @DisplayName("1.11 Validator throws exception when email address is white space")
        public void testExceptionThrownWhenEmailAddressIsWhiteSpace() {
            // Arrange
            String invalidTestEmailAddress = "   ";
            // Act
            // Assert
            assertThrows(IllegalArgumentException.class, () -> Validator.validateEmailAddress(invalidTestEmailAddress));
        }

        @Test
        @DisplayName("1.12 Validator throws exception when email address is not in the correct format")
        public void testExceptionThrownWhenEmailAddressIsNotInCorrectFormat() {
            // Arrange
            String invalidTestEmailAddress = "hello@world.";
            // Act
            // Assert
            assertThrows(IllegalArgumentException.class, () -> Validator.validateEmailAddress(invalidTestEmailAddress));
        }
    }
}
