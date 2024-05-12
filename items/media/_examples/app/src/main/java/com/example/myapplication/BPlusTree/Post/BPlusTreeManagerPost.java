package com.example.myapplication.BPlusTree.Post;

import android.content.Context;
import android.util.Log;

import com.example.myapplication.BPlusTree.BPlusTree;
import com.example.myapplication.src.Parser.Keywords.KeywordExtractor;
import com.example.myapplication.src.Post;
import com.example.myapplication.src.SearchManager;
import com.example.myapplication.src.Tag;

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


    public static List<Post> randomRecommender(Context context) {
        List<Post> allPosts = BPlusTreeManagerPost.getTreeInstance(context).queryAllData();
        Collections.shuffle(allPosts);
        List<Post> selectedPosts = new ArrayList<>();
        for (int i = 0; i < Math.min(8, allPosts.size()); i++) {
            selectedPosts.add(allPosts.get(i));
        }
        return selectedPosts;
    }

    public static List<Post> searchKeyword(Context context, String input) {
        List<Post> allPosts = BPlusTreeManagerPost.getTreeInstance(context).queryAllData();
        List<Post> filteredRes = new ArrayList<>();
        List<String> keywords = new KeywordExtractor().extractKeyWords(input);
        if (keywords.isEmpty()) {
            Log.d("Keywords results", "Keywords results are empty");
        }


        for (String keyword : keywords) {
            Log.d("Keywords results", "Keywords Results are " + keyword);
        }

        for (Post post : allPosts) {
            String description = post.getDescription();
            String title = post.getProductDisplayName();
            for (String keyword : keywords) {
                if (description.trim().toLowerCase().contains(keyword.trim().toLowerCase())
                        || keyword.trim().toLowerCase().contains(title.trim().toLowerCase())) {
                    filteredRes.add(post);
                }
            }
        }
        return filteredRes;
    }

    public static Post searchPostId(Context context, String id) {
        List<Post> allPosts = BPlusTreeManagerPost.getTreeInstance(context).queryAllData();
        for (Post post : allPosts) {
            String postId = post.getPostID();
            if (postId.equals(id)) {
                return post;
            }
        }
        return null;
    }


}
