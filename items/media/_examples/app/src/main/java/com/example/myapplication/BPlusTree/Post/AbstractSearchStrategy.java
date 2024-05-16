package com.example.myapplication.BPlusTree.Post;

import android.content.Context;

import com.example.myapplication.src.Post;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Yichi Zhang
 */
public abstract class AbstractSearchStrategy implements SearchStrategy {
    @Override
    public List<Post> search(Context context, String... values) {
        List<Post> allPosts = BPlusTreeManagerPost.getTreeInstance(context).queryAllData();
        List<Post> filteredPosts = new ArrayList<>();

        for (Post post : allPosts) {
            if (matchCriteria(post, values)) {
                filteredPosts.add(post);
            }
        }

        return filteredPosts;
    }

    protected abstract boolean matchCriteria(Post post, String... values);
}