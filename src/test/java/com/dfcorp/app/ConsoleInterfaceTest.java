package com.dfcorp.app;

import com.ginsberg.junit.exit.ExpectSystemExitWithStatus;
import org.junit.jupiter.api.*;
import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class ConsoleInterfaceTest {

    @Nested
    @DisplayName("Console Interface Main Menu Tests")
    public class ConsoleInterfaceMainMenuTests {

        @Test
        @DisplayName("7.1 Throws exception when main menu input is empty")
        public void testExceptionWhenMainMenuInputIsEmpty() {
            // Assign
            // Act
            String testInput = "";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            System.setIn(inputStream);
            // Assert
            assertThrows(Exception.class, () -> {ConsoleInterface.mainMenu();});
        }

        @Test
        @DisplayName("7.2 Throws exception when input is not a number between 0 and 5")
        public void testExceptionWhenMainMenuInputIsNotBetween0and5() {
            // Assign
            // Act
            String testInput = "1";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            System.setIn(inputStream);
            // Assert
            assertThrows(Exception.class, () -> {ConsoleInterface.mainMenu();});
        }

        @Test
        @DisplayName("7.3 Exits the app when input is 0")
        @ExpectSystemExitWithStatus(0)
        public void testExitsAppWhenInputIs0() throws Exception {
            // Assign
            // Act
            String testInput = "0";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            System.setIn(inputStream);
            ConsoleInterface.mainMenu();
            // Assert - Uses com.ginsberg.junit.exit dependency to test whether system exit has been called
        }
    }

    @Nested
    @DisplayName("Console Interface Add Contact Interface Tests")
    public class ConsoleInterfaceAddContactTests {

        @Test
        @DisplayName("7.4 Takes three inputs from user in add contact interface")
        public void testTakes3InputsInAddContactInterface() {
            // Assign
            String testInput = "Aidan Adams\n01234567890\naidanadams@abc.com";
            String[] expected = {"Aidan Adams", "01234567890", "aidanadams@abc.com"};
            // Act
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            System.setIn(inputStream);
            String[] contactDetails = ConsoleInterface.addContactInterface();
            // Assert
            assertArrayEquals(expected, contactDetails);
        }
    }

    @Nested
    @DisplayName("Console Interface Edit Contact Interface Tests")
    public class ConsoleInterfaceEditContactTests {

        @Test
        @DisplayName("7.5 Takes three inputs from user in edit contact interface")
        public void testTakes3InputsInEditContactInterface() {
            // Assign
            String testInput = "Aidan Adams\nname\nChris Clay";
            String[] expected = {"Aidan Adams", "name", "Chris Clay"};
            // Act
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            System.setIn(inputStream);
            String[] contactDetails = ConsoleInterface.editContactInterface();
            // Assert
            assertArrayEquals(expected, contactDetails);
        }
    }

    @Nested
    @DisplayName("Console Interface Remove Contact Interface Tests")
    public class ConsoleInterfaceRemoveContactTests {

        @Test
        @DisplayName("7.6 Takes one input from user in remove contact interface")
        public void testTakes1InputInRemoveContactInterface() {
            // Assign
            String testInput = "Aidan Adams";
            // Act
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            System.setIn(inputStream);
            String contactName = ConsoleInterface.removeContactInterface();
            // Assert
            assertEquals(testInput, contactName);
        }
    }
}
