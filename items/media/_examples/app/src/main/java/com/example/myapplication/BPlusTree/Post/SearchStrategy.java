package com.example.myapplication.BPlusTree.Post;

import android.content.Context;

import com.example.myapplication.src.Post;

import java.util.List;

/**
 * @author Yichi Zhang
 */
public interface SearchStrategy {
    List<Post> search(Context context, String... values);
}