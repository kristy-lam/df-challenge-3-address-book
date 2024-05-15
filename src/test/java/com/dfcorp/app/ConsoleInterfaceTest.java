package com.dfcorp.app;

import org.junit.jupiter.api.*;
import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class ConsoleInterfaceTest {

    @Test
    @DisplayName("7.1 Throws exception when main menu input is empty")
    public void testExceptionWhenMainMenuInputIsEmpty() {
        // Assign
        String testInput = "";
        // Act
        // Reassign standard input stream of System.in as test input
        InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
        System.setIn(inputStream);
        // Assert
        assertThrows(Exception.class, () -> {ConsoleInterface.mainMenu();});
    }

    @Test
    @DisplayName("7.2 Throws exception when input is not a number between 0 and 6")
    public void testExceptionWhenMainMenuInputIsNotBetween0and6() {
        // Assign
        String testInput = "9";
        // Act
        // Reassign standard input stream of System.in as test input
        InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
        System.setIn(inputStream);
        // Assert
        assertThrows(Exception.class, () -> {ConsoleInterface.mainMenu();});
    }

    @Test
    @DisplayName("8.2 Interface prompts user to confirm deletion")
    public void testPromptsForDeletionConfirmation() {
        // Assign
        String testInput = "n";
        // Act
        // Reassign standard input stream of System.in as test input
        InputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
        System.setIn(inputStream);
        boolean actual = ConsoleInterface.promptDeletionConfirmation();
        // Assert
        assertFalse(actual);
    }

}
