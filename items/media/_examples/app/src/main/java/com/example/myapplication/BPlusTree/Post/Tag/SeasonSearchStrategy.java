package com.example.myapplication.BPlusTree.Post.Tag;

import android.util.Log;

import com.example.myapplication.BPlusTree.Post.AbstractSearchStrategy;
import com.example.myapplication.src.Post;

/**
 * @author Yichi Zhang
 */
public class SeasonSearchStrategy extends AbstractSearchStrategy {
    @Override
    protected boolean matchCriteria(Post post, String... value) {
        if (value.length > 0) {
            String season = value[0];
            Log.d("SeasonSearchStrategy", "matchCriteria: " + post.getTag().getSeason().equals(season));
            return post.getTag().getSeason().equals(season);
        }
        return false;
    }
}
