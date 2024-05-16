package com.example.myapplication.BPlusTree.Post.Tag;

import android.util.Log;

import com.example.myapplication.BPlusTree.Post.AbstractSearchStrategy;
import com.example.myapplication.src.Post;

/**
 * The GenderSearchStrategy class extends the AbstractSearchStrategy class.
 * It provides a specific implementation of the matchCriteria method that checks if a post's gender matches a specified value.
 *
 * @see AbstractSearchStrategy
 * @see Post
 * @author Yichi Zhang
 */
public class GenderSearchStrategy extends AbstractSearchStrategy {

    /**
     * Checks if a post's gender matches the specified value.
     * This method logs the result of the comparison and returns it.
     * If no value is specified, this method returns false.
     *
     * @param post the post to check
     * @param value the value to match against, expected to be the gender
     * @return true if the post's gender matches the specified value, false otherwise
     */
    @Override
    protected boolean matchCriteria(Post post, String... value) {
        if (value.length > 0) {
            String gender = value[0];
            Log.d("GenderSearchStrategy", "matchCriteria: " + post.getTag().getGender().equals(gender));
            return post.getTag().getGender().equals(gender);
        }
        return false;
    }
}