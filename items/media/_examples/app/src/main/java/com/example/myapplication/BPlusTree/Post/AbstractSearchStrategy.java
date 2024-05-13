package com.example.myapplication.BPlusTree.Post;

import android.content.Context;

import com.example.myapplication.src.Post;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSearchStrategy implements SearchStrategy {
    @Override
    public List<Post> search(Context context, String value) {
        List<Post> allPosts = BPlusTreeManagerPost.getTreeInstance(context).queryAllData();
        List<Post> filteredPosts = new ArrayList<>();

        for (Post post : allPosts) {
            if (matchCriteria(post, value)) {
                filteredPosts.add(post);
            }
        }

        return filteredPosts;
    }

    protected abstract boolean matchCriteria(Post post, String value);
}
