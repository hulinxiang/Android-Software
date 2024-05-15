package com.example.myapplication.BPlusTree.Post.Tag;

import android.util.Log;

import com.example.myapplication.BPlusTree.Post.AbstractSearchStrategy;
import com.example.myapplication.src.Post;

public class GenderSearchStrategy extends AbstractSearchStrategy {
//    @Override
//    protected boolean matchCriteria(Post post, String... value) {
//        Log.d("GenderSearchStrategy", "matchCriteria: " + post.getTag().getGender().equals(value));
//        return post.getTag().getGender().equals(value);
//    }
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
