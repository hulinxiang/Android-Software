package com.example.myapplication.BPlusTree.Post.Tag;

import com.example.myapplication.BPlusTree.Post.AbstractSearchStrategy;
import com.example.myapplication.src.Post;

public class UsageSearchStrategy extends AbstractSearchStrategy {
    @Override
    protected boolean matchCriteria(Post post, String value) {
        return post.getTag().getUsage().equals(value);
    }
}
