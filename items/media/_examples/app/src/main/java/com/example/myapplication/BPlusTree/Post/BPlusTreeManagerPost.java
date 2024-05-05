package com.example.myapplication.BPlusTree.Post;

import android.content.Context;

import com.example.myapplication.BPlusTree.BPlusTree;
import com.example.myapplication.src.Post;
import com.example.myapplication.src.SearchManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class BPlusTreeManagerPost {
    private static BPlusTree<String, Post> postTree;

    public static synchronized BPlusTree<String, Post> getTreeInstance(Context context) {
        if (postTree == null) {
            postTree = new BPlusTree<>();
        }
        return postTree;
    }


    public static ArrayList<Post> randomRecommender(Context context) {
        List<Post> allPosts = BPlusTreeManagerPost.getTreeInstance(context).queryAllData(); // 使用正确的方法调用
        Collections.shuffle(allPosts);
        ArrayList<Post> selectedPosts = new ArrayList<>();
        for (int i = 0; i < Math.min(4, allPosts.size()); i++) {
            selectedPosts.add(allPosts.get(i));
        }
        return selectedPosts;
    }

}
