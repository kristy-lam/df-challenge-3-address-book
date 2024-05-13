package com.dfcorp.app;

import org.junit.jupiter.api.*;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;

public class ConsoleInterfaceTest {

    @Nested
    @DisplayName("Console Interface Initialisation Tests")
    public class ConsoleInterfaceInitialisationTests {

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
    }

}
