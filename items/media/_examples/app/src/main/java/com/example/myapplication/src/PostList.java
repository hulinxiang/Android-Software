package com.example.myapplication.src;

import java.util.ArrayList;
import java.util.List;

/**
 * The PostList class represents a list of Post objects.
 * It provides methods to check if the list is empty and to retrieve a specific post by index.
 *
 * @Author Yichi Zhang
 */
public class PostList {
    private List<Post> posts = new ArrayList<>();

    /**
     * Checks if the post list is empty.
     *
     * @return true if the post list is empty, false otherwise
     */
    public boolean isEmpty() {
        return posts.isEmpty();
    }

    /**
     * Retrieves a specific post by index.
     *
     * @param index the index of the post to retrieve
     * @return the post at the specified index
     */
    public Post get(int index) {
        return posts.get(index);
    }
}