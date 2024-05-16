package com.example.myapplication.BPlusTree.Post;

import android.content.Context;

import com.example.myapplication.src.Post;

import java.util.ArrayList;
import java.util.List;

/**
 * The AbstractSearchStrategy class provides a template for creating search strategies.
 * It implements the SearchStrategy interface and provides a default implementation for the search method.
 * Subclasses must implement the matchCriteria method to define the criteria for filtering posts.
 *
 * @see SearchStrategy
 * @see Post
 * @see BPlusTreeManagerPost
 * @see AbstractSearchStrategy
 * @author Yichi Zhang u7748799
 */
public abstract class AbstractSearchStrategy implements SearchStrategy {

    /**
     * Searches for posts that match the specified values.
     * This method retrieves all posts from the BPlusTree and filters them based on the matchCriteria method.
     *
     * @param context the context in which the search is performed
     * @param values the values to match against
     * @return a list of posts that match the specified values
     */
    @Override
    public List<Post> search(Context context, String... values) {
        List<Post> allPosts = BPlusTreeManagerPost.getTreeInstance(context).queryAllData();
        List<Post> filteredPosts = new ArrayList<>();

        for (Post post : allPosts) {
            if (matchCriteria(post, values)) {
                filteredPosts.add(post);
            }
        }

        return filteredPosts;
    }

    /**
     * Checks if a post matches the specified criteria.
     * This method is abstract and must be implemented by subclasses to define the criteria for filtering posts.
     *
     * @param post the post to check
     * @param values the values to match against
     * @return true if the post matches the criteria, false otherwise
     */
    protected abstract boolean matchCriteria(Post post, String... values);
}