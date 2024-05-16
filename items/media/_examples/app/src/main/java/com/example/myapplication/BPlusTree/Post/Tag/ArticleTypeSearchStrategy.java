package com.example.myapplication.BPlusTree.Post.Tag;

import android.util.Log;

import com.example.myapplication.BPlusTree.Post.AbstractSearchStrategy;
import com.example.myapplication.src.Post;

/**
 * The ArticleTypeSearchStrategy class extends the AbstractSearchStrategy class.
 * It provides a specific implementation of the matchCriteria method that checks if a post's article type matches a specified value.
 *
 * @see AbstractSearchStrategy
 * @see Post
 * @author Yichi Zhang u7748799
 */
public class ArticleTypeSearchStrategy extends AbstractSearchStrategy {

    /**
     * Checks if a post's article type matches the specified value.
     * This method logs the result of the comparison and returns it.
     * If no value is specified, this method returns false.
     *
     * @param post the post to check
     * @param value the value to match against, expected to be the article type
     * @return true if the post's article type matches the specified value, false otherwise
     */
    @Override
    protected boolean matchCriteria(Post post, String... value) {
        if (value.length > 0) {
            String articleType = value[0];
            Log.d("ArticleTypeSearchStrategy", "matchCriteria: " + post.getTag().getArticleType().equals(articleType));
            return post.getTag().getArticleType().equals(articleType);
        }
        return false;
    }
}