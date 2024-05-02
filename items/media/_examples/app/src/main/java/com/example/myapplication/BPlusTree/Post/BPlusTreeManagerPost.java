package com.example.myapplication.BPlusTree.Post;

import android.content.Context;

import com.example.myapplication.BPlusTree.BPlusTree;
import com.example.myapplication.src.Post;


public class BPlusTreeManagerPost {
    private static BPlusTree<String, Post> postTree;

    public static synchronized BPlusTree<String, Post> getTreeInstance(Context context) {
        if (postTree == null) {
            postTree = new BPlusTree<>();
        }
        return postTree;
    }

}
