package com.example.myapplication.BPlusTree.Post;

import android.content.Context;

import com.example.myapplication.src.Post;

import java.util.List;

public interface SearchStrategy {
    List<Post> search(Context context, String value);
}
