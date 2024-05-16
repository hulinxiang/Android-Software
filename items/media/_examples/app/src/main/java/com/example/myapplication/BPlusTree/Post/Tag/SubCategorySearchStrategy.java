package com.example.myapplication.BPlusTree.Post.Tag;

import android.util.Log;

import com.example.myapplication.BPlusTree.Post.AbstractSearchStrategy;
import com.example.myapplication.src.Post;

/**
 * The SubCategorySearchStrategy class extends the AbstractSearchStrategy class.
 * It provides a specific implementation of the matchCriteria method that checks if a post's sub category matches a specified value.
 *
 * @see AbstractSearchStrategy
 * @see Post
 * @Author Yichi Zhang u7748799
 */
public class SubCategorySearchStrategy extends AbstractSearchStrategy {

    /**
     * Checks if a post's sub category matches the specified value.
     * This method logs the result of the comparison and returns it.
     * If no value is specified, this method returns false.
     *
     * @param post the post to check
     * @param value the value to match against, expected to be the sub category
     * @return true if the post's sub category matches the specified value, false otherwise
     */
    @Override
    protected boolean matchCriteria(Post post, String... value) {
        if (value.length > 0) {
            String subCategory = value[0];
            return post.getTag().getSubCategory().equals(subCategory);
        }
        return false;
    }
}