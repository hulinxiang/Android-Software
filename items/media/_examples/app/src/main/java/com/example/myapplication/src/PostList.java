package com.example.myapplication.src;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jin Yang, Yichi Zhang
 */
public class PostList {
    private List<Post> posts = new ArrayList<>();

    // Check if the post list is empty
    public boolean isEmpty() {
        return posts.isEmpty();
    }

    // Retrieve a specific post by index
    public Post get(int index) {
        return posts.get(index);
    }
}
