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


public class FirebasePostHelper {

    public void addPost(Post post) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("post");


        Log.d("Firebase add operation", "Enter the method");

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("Firebase add operation", "Execute the method");
                // 这将给出"user"下子节点的数量
                long count = dataSnapshot.getChildrenCount();
                // 现在在这个新索引下设置新post数据
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


    public void updatePost(Post post) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
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
