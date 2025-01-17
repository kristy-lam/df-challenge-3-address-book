package com.dfcorp.app;

import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class ConsoleInterface {

    public static String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static int mainMenu() {
        mainMenuText();
        String userStrInput = getInput();
        int userInput = parseInt(userStrInput);
        if (userInput < 0 || userInput > 6) {
            throw new IllegalArgumentException("Invalid input, please try again.");
        }
        return userInput;
    }

    private static void mainMenuText() {
        System.out.println("========== Welcome to your DFCorp Address Book App! ==========");
        System.out.println("Please choose from the main menu below:");
        System.out.println("1 - Add a contact");
        System.out.println("2 - Edit a contact");
        System.out.println("3 - Remove a contact");
        System.out.println("4 - Search and view contact(s)");
        System.out.println("5 - View all contacts");
        System.out.println("6 - Delete all contacts");
        System.out.println("0 - Exit the app");
        System.out.println("==============================================================");
        System.out.println("Please input a number: ");
    }

    public static boolean promptDeletionConfirmation() {
        System.out.println("Are you sure you want to delete all contacts? Enter y to delete.");
        String userInput = getInput();
        return userInput.equals("y");
    }

}
