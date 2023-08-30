package com.sarmad;

import com.sarmad.Exceptions.InvalidValueTypeException;
import com.sarmad.Exceptions.InvalidMenuOptionException;
import com.sarmad.Menu.Menu;
import com.sarmad.Menu.MenuHandler;
import com.sarmad.SocialMedia.SocialMediaManager;
import com.sarmad.SocialMedia.SocialMediaPost;
import com.sarmad.Utils.PrintResult;
import com.sarmad.Utils.SortBy;

import java.util.List;
import java.util.Scanner;

public class Main {

    static final String FILE_PATH = "data/posts.csv";

    public static void main(String[] args) {
        SocialMediaManager socialMediaManager = new SocialMediaManager();

        Scanner scanner = new Scanner(System.in);

        //to catch exception
        MenuHandler menuHandler = new MenuHandler(scanner);

        socialMediaManager.readPostsFromCSV(FILE_PATH);

        boolean shouldExit = false;
        System.out.println("Welcome to Social Media Analyzer!");
        while (!shouldExit) {

            int option = 0;
            try {
                Menu.displayMenu();
                option = menuHandler.readMenuChoice();

                switch (option) {
                    case 1 -> {
                        System.out.println("Add post to social media selected");
                        SocialMediaPost newPost = null;
                        try {
                            newPost = getNewPostInput(scanner);
                            socialMediaManager.addPOst(newPost);
                        } catch (InvalidValueTypeException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    case 2 -> {
                        System.out.println("Delete post by ID");
                        int idToDelete = Integer.parseInt(scanner.nextLine());
                        boolean deleted = socialMediaManager.deletePostById(idToDelete);
                        System.out.println("deleted: " + deleted);
                    }
                    case 3 -> {
                        System.out.println("Retrieve post by ID");
                        int lookUpId = Integer.parseInt(scanner.nextLine());
                        SocialMediaPost socialMediaPostResult = socialMediaManager.findPostById(lookUpId);
                        if (socialMediaPostResult == null) {
                            System.out.println("Sorry the post does not exist in the collection!");
                        } else {
                            PrintResult.formatAndPrintResult(new SocialMediaPost[]{socialMediaPostResult});
                        }
                    }
                    case 4 -> {
                        System.out.println("Retrieve top N posts by likes");
                        int noOfResultsSortedByLikes = Integer.parseInt(scanner.nextLine());
                        List<SocialMediaPost> results = socialMediaManager.getTopNPostsBy(SortBy.LIKES, noOfResultsSortedByLikes);
                        PrintResult.formatAndPrintResult(results.toArray(new SocialMediaPost[0]));
                    }
                    case 5 -> {
                        System.out.println("Retrieve top N posts by shares");
                        int noOfResultsSortedByShares = Integer.parseInt(scanner.nextLine());
                        List<SocialMediaPost> resultsSortedByShares = socialMediaManager.getTopNPostsBy(SortBy.SHARES, noOfResultsSortedByShares);
                        PrintResult.formatAndPrintResult(resultsSortedByShares.toArray(new SocialMediaPost[0]));
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
            } catch (InvalidMenuOptionException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void exitProgram() {
        System.out.println("Thanks for using Social Media Analyzer!");
    }

    private static SocialMediaPost getNewPostInput(Scanner scanner) throws InvalidValueTypeException {
        try {
            var socialMediaPost = new SocialMediaPost();

            System.out.println("Please provide the post ID: ");
            int id = Integer.parseInt(scanner.nextLine());
            socialMediaPost.setId(id);

            System.out.println("Please provide the post content: ");
            String content = scanner.nextLine();
            socialMediaPost.setContent(content);

            System.out.println("Please provide the post author: ");
            String author = scanner.nextLine();
            socialMediaPost.setAuthor(author);

            System.out.println("Please provide the number of likes of the post: ");
            int likes = Integer.parseInt(scanner.nextLine());
            socialMediaPost.setLikes(likes);

            System.out.println("Please provide the number of shares of the post: ");
            int shares = Integer.parseInt(scanner.nextLine());
            socialMediaPost.setShares(shares);

            System.out.println("Please provide the date and time of the post in the format of DD/MM/YYYY HH:MM: ");
            String dateTime = scanner.nextLine();
            socialMediaPost.setDatetime(dateTime);

            return socialMediaPost;
        } catch (NumberFormatException numEx) {
            throw new InvalidValueTypeException(numEx.getMessage());
        }
    }
}


