package com.sarmad.Utils;

import com.sarmad.SocialMedia.SocialMediaPost;

public class PrintResult {

    public static void formatAndPrintResult(SocialMediaPost[] posts) {
        System.out.println("Result(s):\n");
        for (int i = 0; i < posts.length; i++) {
            System.out.printf("%d) %d | %s | %d | %d\n",
                    i+1,
                    posts[i].getId(),
                    posts[i].getContent(),
                    posts[i].getShares(),
                    posts[i].getLikes());
        }

    }
}
