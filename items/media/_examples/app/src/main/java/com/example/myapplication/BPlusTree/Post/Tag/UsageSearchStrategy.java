package com.example.myapplication.BPlusTree.Post.Tag;

import android.util.Log;

import com.example.myapplication.BPlusTree.Post.AbstractSearchStrategy;
import com.example.myapplication.src.Post;

/**
 * @author Yichi Zhang
 */
public class UsageSearchStrategy extends AbstractSearchStrategy {
    @Override
    protected boolean matchCriteria(Post post, String... value) {
        if (value.length > 0) {
            String usage = value[0];
            Log.d("UsageSearchStrategy", "matchCriteria: " + post.getTag().getUsage().equals(usage));
            return post.getTag().getUsage().equals(usage);
        }
        return false;
    }
}
