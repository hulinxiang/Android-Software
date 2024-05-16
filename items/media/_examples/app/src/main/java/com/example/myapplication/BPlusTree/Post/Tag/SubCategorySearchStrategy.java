package com.example.myapplication.BPlusTree.Post.Tag;

import android.util.Log;

import com.example.myapplication.BPlusTree.Post.AbstractSearchStrategy;
import com.example.myapplication.src.Post;

public class SubCategorySearchStrategy extends AbstractSearchStrategy {
    @Override
    protected boolean matchCriteria(Post post, String... value) {
        if (value.length > 0) {
            String subCategory = value[0];
            Log.d("SubCategorySearchStrategy", "matchCriteria: " + post.getTag().getSubCategory().equals(subCategory));
            return post.getTag().getSubCategory().equals(subCategory);
        }
        return false;
    }
}
