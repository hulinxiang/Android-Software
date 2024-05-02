package com.example.myapplication.BPlusTree.User_Post;

import android.content.Context;

import com.example.myapplication.BPlusTree.BPlusTree;

import java.util.List;


public class BPlusTreeManagerUser_Post {
    private static BPlusTree<String, List<String>> user_postTree;

    public static synchronized BPlusTree<String, List<String>> getTreeInstance(Context context) {
        if (user_postTree == null) {
            user_postTree = new BPlusTree<>();
        }
        return user_postTree;
    }

}
