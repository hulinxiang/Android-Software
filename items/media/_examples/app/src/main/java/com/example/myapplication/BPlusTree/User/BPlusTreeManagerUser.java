package com.example.myapplication.BPlusTree.User;

import android.content.Context;

import com.example.myapplication.BPlusTree.BPlusTree;

import com.example.myapplication.src.User;


public class BPlusTreeManagerUser {
    private static BPlusTree<String, User> userTree;

    public static synchronized BPlusTree<String, User> getTreeInstance(Context context) {
        if (userTree == null) {
            userTree = new BPlusTree<>();
        }
        return userTree;
    }
}
