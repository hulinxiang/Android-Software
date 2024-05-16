package com.example.myapplication.src.Firebase.PostManager;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.myapplication.src.Post;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

/**
 * @author Linxiang Hu
 * Helper class for Firebase post management operations.
 * Provides methods to add, update, and delete posts in the Firebase Realtime Database.
 */
public class FirebasePostHelper {

    /**
     * Adds a new post to the Firebase database under the "post" node.
     *
     * @param post The Post object containing the post details to be stored.
     */
    public void addPost(Post post) {
        // Get the singleton instance of FirebaseDatabase.
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        // Get a reference to the "post" node.
        DatabaseReference myRef = database.getReference().child("post");

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("Firebase add operation", "Execute the method");
                // Get the number of children under "post".
                long count = dataSnapshot.getChildrenCount();
                // Set post data in Firebase under the new node.
                DatabaseReference newPostRef = myRef.child(String.valueOf(count));
                newPostRef.child("UserID").setValue(post.getUserID());
                newPostRef.child("articleType").setValue(post.getTag().getArticleType());
                newPostRef.child("baseColour").setValue(post.getTag().getBaseColour());
                newPostRef.child("comment").setValue(post.getComments());   //这个是不是有问题。（tyx问
                newPostRef.child("description").setValue(post.getDescription());
                newPostRef.child("gender").setValue(post.getTag().getGender());
                newPostRef.child("image_url").setValue(post.getImageUrl());
                newPostRef.child("masterCategory").setValue(post.getTag().getMasterCategory());
                newPostRef.child("postID").setValue(post.getPostID());
                newPostRef.child("postIndexInFirebase").setValue(post.getPostIndexInFirebase());
                newPostRef.child("price").setValue(Double.toString(post.getPrice()));
                newPostRef.child("productDisplayName").setValue(post.getProductDisplayName());
                newPostRef.child("season").setValue(post.getTag().getSeason());
                newPostRef.child("status").setValue(post.getStatus());
                newPostRef.child("subCategory").setValue(post.getTag().getSubCategory());
                newPostRef.child("year").setValue(post.getTag().getYear());
                newPostRef.child("usage").setValue(post.getTag().getUsage());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Firebase add operation failed", "Failure to add post to firebase：" + databaseError.getCode());
            }
        });
    }


    /**
     * Updates an existing post in the Firebase database.
     *
     * @param post The Post object containing the updated details.
     */
    public void updatePost(Post post) {
        // Get the singleton instance of FirebaseDatabase.
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        // Get a reference to the "post" node.
        DatabaseReference myRef = database.getReference().child("post");
        String curPostId = post.getPostID();

        Log.d("Firebase update operation", "Enter the method");

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("Firebase update operation", "Execute the method");
                int count = 0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (Objects.equals(snapshot.child("postId").getValue(String.class), curPostId)) {
                        DatabaseReference newPostRef = myRef.child(String.valueOf(count));
                        // get the reference to the node that needs update.
                        newPostRef.child("UserID").setValue(post.getUserID());
                        newPostRef.child("articleType").setValue(post.getTag().getArticleType());
                        newPostRef.child("baseColour").setValue(post.getTag().getBaseColour());
                        newPostRef.child("comment").setValue(post.getComments());
                        newPostRef.child("description").setValue(post.getDescription());
                        newPostRef.child("gender").setValue(post.getTag().getGender());
                        newPostRef.child("image_url").setValue(post.getImageUrl());
                        newPostRef.child("masterCategory").setValue(post.getTag().getMasterCategory());
                        newPostRef.child("postID").setValue(post.getPostID());
                        newPostRef.child("postIndexInFirebase").setValue(post.getPostIndexInFirebase());
                        newPostRef.child("price").setValue(Double.toString(post.getPrice()));
                        newPostRef.child("productDisplayName").setValue(post.getProductDisplayName());
                        newPostRef.child("season").setValue(post.getTag().getSeason());
                        newPostRef.child("status").setValue(post.getStatus());
                        newPostRef.child("subCategory").setValue(post.getTag().getSubCategory());
                        newPostRef.child("year").setValue(post.getTag().getYear());
                        newPostRef.child("usage").setValue(post.getTag().getUsage());
                        break;
                    }
                    count++;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Firebase update operation failed", "Failure to update post to firebase：" + databaseError.getCode());
            }
        });
    }

    /**
     * Deletes a post from the Firebase database.
     * @param post The Post object that identifies the post to be deleted.
     */
    public void deletePost(Post post) {
        // Get the singleton instance of FirebaseDatabase.
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        // Get a reference to the "post" node.
        DatabaseReference myRef = database.getReference().child("post");
        String curPostId = post.getPostID();

        Log.d("Firebase delete operation", "Enter the method");

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("Firebase delete operation", "Execute the method");
                int count = 0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (Objects.equals(snapshot.child("postId").getValue(String.class), curPostId)) {
                        DatabaseReference newPostRef = myRef.child(String.valueOf(count));
                        newPostRef.removeValue(); // Remove the remark from Firebase.
                        break;
                    }
                    count++;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Firebase delete operation failed", "Failure to delete post to firebase：" + databaseError.getCode());
            }
        });
    }
}