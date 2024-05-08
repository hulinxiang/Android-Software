package com.example.myapplication.activity.Image;


import android.content.Context;

import com.example.myapplication.BPlusTree.Post.BPlusTreeManagerPost;
import com.example.myapplication.src.Firebase.PostManager.FirebasePostManager;
import com.example.myapplication.src.Post;
import com.example.myapplication.src.SessionManager;
import com.example.myapplication.src.User;

public class PostCreator {
    private Context context;

    public PostCreator(Context context) {
        this.context = context;
    }

    public void createPost(String userID, String gender, String masterCategoryName, String subCategoryName,
                           String articleTypeName, String baseColour, String season, int year, String usage,
                           String productDisplayName, double productPrice, String productStatus, String imageUrl,
                           String productDescription, String commentText) {
        // Create a new post
        Post newPost = new Post(userID, gender, masterCategoryName, subCategoryName, articleTypeName,
                baseColour, season, year, usage, productDisplayName, productPrice, productStatus, imageUrl,
                productDescription, commentText);

        // Associate the post with the current user
        User currentUser = SessionManager.getInstance().getUser();
        if (currentUser != null) {
            currentUser.addOwnPost(newPost);
        }

        // Save the post to the BPlus Tree
        BPlusTreeManagerPost.getTreeInstance(context).insert(newPost.getPostID(), newPost);

        // Save the post to Firebase
        FirebasePostManager.getInstance(context).addPost(newPost);
    }
}
