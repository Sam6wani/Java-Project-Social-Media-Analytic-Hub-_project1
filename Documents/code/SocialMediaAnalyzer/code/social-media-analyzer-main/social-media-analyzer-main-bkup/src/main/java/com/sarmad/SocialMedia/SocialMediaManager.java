package com.sarmad.SocialMedia;

import com.sarmad.Utils.SortBy;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class SocialMediaManager {

    final int NO_OF_COLUMNS = 6;

    private List<SocialMediaPost> posts;

    public SocialMediaManager() {
        this.posts = new ArrayList<>();
    }

    public void readPostsFromCSV(String filePath) {

        try (Scanner scanner = new Scanner(new File(getClass().getClassLoader().getResource("data/posts.csv").getFile()))) {
            // this is to ignore first line containing headers
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] columns = line.split(",", NO_OF_COLUMNS);
                int id = Integer.parseInt(columns[0]);
                String content = columns[1];
                String author = columns[2];
                int likes = Integer.parseInt(columns[3]);
                int shares = Integer.parseInt(columns[4]);
                String dateTime = columns[5];

                posts.add(new SocialMediaPost(id, content, author, likes, shares, dateTime));
            }

//            System.out.println(posts.toArray().length);
        } catch (FileNotFoundException ex) {
            System.out.println("Error: File not found.");
        }
    }

    public List<SocialMediaPost> getTopNPostsBy(SortBy sortBy, int noOfResultsToReturn) {
        if (noOfResultsToReturn <= 0) {
            return null;
        }

        List<SocialMediaPost> sortedPosts = new ArrayList<>(posts);

        sortedPosts.sort(Comparator.comparingInt(sortBy == SortBy.LIKES ? SocialMediaPost::getLikes : SocialMediaPost::getShares).reversed());

//        sortedPosts.sort(Comparator.comparingInt(SocialMediaPost::getLikes).reversed());

        if (noOfResultsToReturn >= posts.size()) {
            return sortedPosts;
        } else {
            return sortedPosts.subList(0, noOfResultsToReturn);
        }
    }

    public List<SocialMediaPost> getTopNPostsByLikes(int noOfResultsToReturn) {
        if (noOfResultsToReturn <= 0) {
            return null;
        }

        List<SocialMediaPost> sortedPosts = new ArrayList<>(posts);
        sortedPosts.sort(Comparator.comparingInt(SocialMediaPost::getLikes).reversed());

        if (noOfResultsToReturn >= posts.size()) {
            return sortedPosts;
        } else {
            return sortedPosts.subList(0, noOfResultsToReturn);
        }
    }

    public void addPOst(SocialMediaPost newPost) {
        posts.add(newPost);
    }

    public boolean deletePostById(int id) {
        return posts.removeIf(post -> post.getId() == id);
    }

    public SocialMediaPost findPostById(int lookUpId) {
        for (SocialMediaPost post : posts) {
            if (post.getId() == lookUpId) {
                return post;
            }
        }
        return null;
    }
}
