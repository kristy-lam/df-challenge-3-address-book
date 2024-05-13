package com.dfcorp.app;

import java.util.Scanner;

import static java.lang.System.exit;

public class ConsoleInterface {

    public static String mainMenu() throws Exception {
        ConsoleInterface.mainMenuText();
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        switch (Integer.parseInt(userInput)) {
            case 0:
                System.out.println("Goodbye!");
                exit(0);
            default:
                throw new Exception("Invalid input, please try again.");
        }
    }

    private static void mainMenuText() {
        System.out.println("========== Welcome to your DFCorp Address Book App! ==========");
        System.out.println("Please choose from the main menu below:");
        System.out.println("1 - Add a contact");
        System.out.println("2 - Edit a contact");
        System.out.println("3 - Remove a contact");
        System.out.println("4 - View a contact");
        System.out.println("5 - View all contacts");
        System.out.println("0 - Exit the app");
        System.out.println("==============================================================");
        System.out.println("Please input a number: ");
    }

}
