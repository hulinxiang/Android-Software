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
 * Author: Linxiang Hu, Yingxuan Tang
 *
 * The FirebasePostHelper class provides methods to add, update, and delete posts in Firebase Realtime Database.
 * It allows interaction with Firebase database for managing post data.
 */
public class FirebasePostHelper {

    /**
     * Adds a new post to Firebase Realtime Database.
     *
     * @param post The post object to be added.
     *
     * Method:
     * - Retrieves the Firebase database instance.
     * - Adds a new post under the "post" node in the database.
     */
    public void addPost(Post post) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("post");

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("Firebase add operation", "Execute the method");
                // This will give the number of "user" subnodes
                long count = dataSnapshot.getChildrenCount();
                // Now set the new post data under this new index
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
     * Deletes a post from Firebase Realtime Database.
     *
     * @param post The post object to be deleted.
     *
     * Method:
     * - Retrieves the Firebase database instance.
     * - Deletes the post with the corresponding post ID from the database.
     */
    public void deletePost(Post post) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
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
                        newPostRef.removeValue();
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