package com.example.myapplication.BPlusTree.Post.Tag;

import android.util.Log;

import com.example.myapplication.BPlusTree.Post.AbstractSearchStrategy;
import com.example.myapplication.src.Post;

public class MasterCategorySearchStrategy extends AbstractSearchStrategy {
    @Override
    protected boolean matchCriteria(Post post, String... value) {
        if (value.length > 0) {
            String masterCategory = value[0];
            Log.d("MasterCategorySearchStrategy", "matchCriteria: " + post.getTag().getMasterCategory().equals(masterCategory));
            return post.getTag().getMasterCategory().equals(masterCategory);
        }
        return false;
    }
}
