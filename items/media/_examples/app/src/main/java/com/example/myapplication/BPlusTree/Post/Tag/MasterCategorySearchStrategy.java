package com.example.myapplication.BPlusTree.Post.Tag;

import android.util.Log;

import com.example.myapplication.BPlusTree.Post.AbstractSearchStrategy;
import com.example.myapplication.src.Post;

/**
 * The MasterCategorySearchStrategy class extends the AbstractSearchStrategy class.
 * It provides a specific implementation of the matchCriteria method that checks if a post's master category matches a specified value.
 *
 * @see AbstractSearchStrategy
 * @see Post
 * @author Yichi Zhang u7748799
 */
public class MasterCategorySearchStrategy extends AbstractSearchStrategy {

    /**
     * Checks if a post's master category matches the specified value.
     * This method logs the result of the comparison and returns it.
     * If no value is specified, this method returns false.
     *
     * @param post the post to check
     * @param value the value to match against, expected to be the master category
     * @return true if the post's master category matches the specified value, false otherwise
     */
    @Override
    protected boolean matchCriteria(Post post, String... value) {
        if (value.length > 0) {
            String masterCategory = value[0];
            return post.getTag().getMasterCategory().equals(masterCategory);
        }
        return false;
    }
}