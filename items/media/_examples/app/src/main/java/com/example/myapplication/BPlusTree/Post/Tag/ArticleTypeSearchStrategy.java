package com.example.myapplication.BPlusTree.Post.Tag;

import android.util.Log;

import com.example.myapplication.BPlusTree.Post.AbstractSearchStrategy;
import com.example.myapplication.src.Post;

/**
 * @author Yichi Zhang
 */
public class ArticleTypeSearchStrategy extends AbstractSearchStrategy {

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
