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
            String testInput = "";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            System.setIn(inputStream);  // System.in provides test input
            // Act
            // Assert
            assertThrows(Exception.class, () -> {ConsoleInterface.mainMenu();});
        }

        @Test
        @DisplayName("7.2 Throws exception when input is not a number between 0 and 5")
        public void testExceptionWhenMainMenuInputIsNotBetween0and5() {
            // Assign
            String testInput = "1";
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            System.setIn(inputStream);  // System.in provides test input
            // Act
            // Assert
            assertThrows(Exception.class, () -> {ConsoleInterface.mainMenu();});
        }

        @Test
        @DisplayName("7.3 Exits the app when input is 0")
        @ExpectSystemExitWithStatus(0)
        public void testExitsAppWhenInputIs0() throws Exception {
            // Assign
            String expected = "Goodbye!";
            String testInput = "0";
            // Act
            InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
            System.setIn(inputStream);  // System.in provides test input of "0"
            ConsoleInterface.mainMenu();
            // Assert - Uses com.ginsberg.junit.exit dependency to test whether system exit has been called
        }
    }
}
