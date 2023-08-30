package com.sarmad;

import com.sarmad.SocialMedia.SocialMediaManager;
import com.sarmad.Menu.Menu;
import com.sarmad.Menu.MenuHandler;

import java.util.Scanner;

public class Main {
    static final String FILE_PATH = "data/posts.csv";

    public static void main(String[] args) {

        // read csv file with posts
        SocialMediaManager socialMediaManager = new SocialMediaManager();
        socialMediaManager.readPostsFromCSV(FILE_PATH);

        // prepare for menu display and input handling
        Scanner scanner = new Scanner(System.in);
        MenuHandler menuHandler = new MenuHandler(scanner);

        // required to handle the program exit
        boolean shouldExit = false;

        // this message is only displayed first time
        System.out.println("Welcome to Social Media Analyzer!");

        while (!shouldExit) {
            // show the application menu
            Menu.displayMenu();

            // get the option entered by the user from the menu displayed
            int option = menuHandler.readMenuChoice();

            // based on the option selected perform various tasks / actions
            // using a switch case here
            switch (option) {
                case 1 -> {
                    System.out.println("Add post to social media selected");
                }
                case 2 -> {
                    System.out.println("Delete post by ID");
                    int idToDelete = Integer.parseInt(scanner.nextLine());
                    System.out.println("Deleted post by ID: " + idToDelete);
                }
                case 3 -> {
                    System.out.println("Retrieve post by ID");
                    int lookUpId = Integer.parseInt(scanner.nextLine());

                    System.out.println("Display post with ID: " + lookUpId);
                }
                case 4 -> {
                    System.out.println("Retrieve top N posts by likes");
                    int noOfResultsSortedByLikes = Integer.parseInt(scanner.nextLine());
                    System.out.println("Display top " + noOfResultsSortedByLikes + " posts sorted by likes");
                }
                case 5 -> {
                    System.out.println("Retrieve top N posts by shares");
                    int noOfResultsSortedByShares = Integer.parseInt(scanner.nextLine());
                    System.out.println("Display top " + noOfResultsSortedByShares + " posts sorted by shares");
                }
                case 6 -> {
                    // not using System.exit(0) as testing becomes tricky because that exist the test thread as well
                    shouldExit = true;
                    scanner.close();
                    exitProgram();
                }
                default -> {
                    System.out.println("Invalid option. Please try again.");
                }
            }
        }
    }

    private static void exitProgram() {
        System.out.println("Thanks for using Social Media Analyzer!");
    }
}