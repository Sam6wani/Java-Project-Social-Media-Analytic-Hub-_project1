package com.sarmad.SocialMedia;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SocialMediaManager {
    final int NO_OF_COLUMNS = 6;

    private List<SocialMediaPost> posts;

    public SocialMediaManager() {
        this.posts = new ArrayList<>();
    }

    public void readPostsFromCSV(String filePath) {
        try (Scanner scanner = new Scanner(new File(getClass().getClassLoader().getResource(filePath).getFile()))) {
            // this is to ignore first line containing headers
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                // read from 2nd line as first is header
                String line = scanner.nextLine();
                // splitting the values in each row with comma
                String[] columns = line.split(",", NO_OF_COLUMNS);

                // don't have to assign these to variables but i have them for now
                int id = Integer.parseInt(columns[0]);
                String content = columns[1];
                String author = columns[2];
                int likes = Integer.parseInt(columns[3]);
                int shares = Integer.parseInt(columns[4]);
                String dateTime = columns[5];

                posts.add(new SocialMediaPost(id, content, author, likes, shares, dateTime));
            }
        } catch (Exception e) { // will update to custom exceptions later
            System.out.println("Error: File not found.");
        }
    }
}
