package com.example.myapplication.BPlusTree.Post;

import android.content.Context;
import com.example.myapplication.src.Post;
import java.util.List;

/**
 * The SearchStrategy interface provides a contract for implementing different search strategies.
 * Each search strategy must implement the search method that takes a context and an array of values as parameters.
 *
 * @see AbstractSearchStrategy
 * @author Yichi Zhang u7748799
 */
public interface SearchStrategy {

    /**
     * Searches for posts that match the specified values.
     *
     * @param context the context in which the search is performed
     * @param values the values to match against
     * @return a list of posts that match the specified values
     */
    List<Post> search(Context context, String... values);
}