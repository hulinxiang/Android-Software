package com.example.myapplication.BPlusTree.Post.Tag;

import android.util.Log;

import com.example.myapplication.BPlusTree.Post.AbstractSearchStrategy;
import com.example.myapplication.src.Post;

/**
 * @author Yichi Zhang
 */
public class BaseColorSearchStrategy extends AbstractSearchStrategy {
    @Override
    protected boolean matchCriteria(Post post, String... value) {
        if (value.length > 0) {
            String baseColour = value[0];
            Log.d("BaseColourSearchStrategy", "matchCriteria: " + post.getTag().getBaseColour().equals(baseColour));
            return post.getTag().getBaseColour().equals(baseColour);
        }
        return false;
    }
}
