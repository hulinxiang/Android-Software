package com.example.myapplication.src;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Author: Yingxuan Tang
 */
public class LikePostManager {
    private SharedPreferences sharedPreferences;
    private DatabaseReference postsRef;

    public LikePostManager(Context context) {
        sharedPreferences = context.getSharedPreferences("like_preferences", Context.MODE_PRIVATE);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        postsRef = database.getReference("post");
    }

    public void likePost(String postId, String userId) {
        // Gets the likes of the current post from local storage
        String likeIds = sharedPreferences.getString(postId, "");

        // Adds the ID of the current user to the like information
        if (!likeIds.contains(userId)) {
            if (!likeIds.isEmpty()) {
                likeIds += ",";
            }
            likeIds += userId;

            // Get the latest likes from Firebase
            String finalLikeIds = likeIds;
            Query query = postsRef.orderByChild("postID").equalTo(postId);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        String firebaseLikeIds = postSnapshot.child("likeIDs").getValue(String.class);
                        if (firebaseLikeIds != null) {
                            //If the like information exists in Firebase, the locally stored like information is merged with the like information in Firebase
                            String[] localIds = finalLikeIds.split(",");
                            String[] firebaseIds = firebaseLikeIds.split(",");
                            Set<String> mergedIds = new HashSet<>();
                            mergedIds.addAll(Arrays.asList(localIds));
                            mergedIds.addAll(Arrays.asList(firebaseIds));
                            String mergedLikeIds = TextUtils.join(",", mergedIds);

                            // Store updated likes locally and to Firebase
                            sharedPreferences.edit().putString(postId, mergedLikeIds).apply();
                            postSnapshot.getRef().child("likeIDs").setValue(mergedLikeIds);
                        } else {
                            // If no likes exist in Firebase, the local likes are directly stored in Firebase
                            sharedPreferences.edit().putString(postId, finalLikeIds).apply();
                            postSnapshot.getRef().child("likeIDs").setValue(finalLikeIds);
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.e("LikePostManager", "Error getting likes from Firebase", databaseError.toException());
                }
            });
        }
    }

    public void unlikePost(String postId, String userId) {
        // Gets the likes of the current post from local storage
        String likeIds = sharedPreferences.getString(postId, "");

        // Removes the current user's ID from the like information
        if (likeIds.contains(userId)) {
            String[] ids = likeIds.split(",");
            Set<String> idSet = new HashSet<>(Arrays.asList(ids));
            idSet.remove(userId);
            String updatedLikeIds = TextUtils.join(",", idSet);

            // Get the latest likes from Firebase
            Query query = postsRef.orderByChild("postID").equalTo(postId);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        String firebaseLikeIds = postSnapshot.child("likeIDs").getValue(String.class);
                        if (firebaseLikeIds != null) {
                            // Merge the likes information in Firebase with the updated likes information locally
                            Set<String> firebaseIdSet = new HashSet<>(Arrays.asList(firebaseLikeIds.split(",")));
                            Set<String> localIdSet = new HashSet<>(Arrays.asList(updatedLikeIds.split(",")));
                            firebaseIdSet.removeAll(localIdSet);
                            String mergedLikeIds = TextUtils.join(",", firebaseIdSet);

                            // Writes the combined likes to Firebase
                            postSnapshot.getRef().child("likeIDs").setValue(mergedLikeIds);
                        }
                    }
                    // Update the locally stored like information
                    sharedPreferences.edit().putString(postId, updatedLikeIds).apply();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.e("LikePostManager", "Error getting likes from Firebase", databaseError.toException());
                }
            });
        }
    }

    public void syncLikes(String postId) {
        Query query = postsRef.orderByChild("postID").equalTo(postId);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String likeIDsFromFirebase = postSnapshot.child("likeIDs").getValue(String.class);
                    String localLikeIDs = sharedPreferences.getString(postId, "");

                    if (likeIDsFromFirebase != null && !localLikeIDs.contains(likeIDsFromFirebase)) {
                        // If the likes exist in Firebase but not in the local storage, the likes in Firebase are synchronized to the local storage
                        sharedPreferences.edit().putString(postId, likeIDsFromFirebase).apply();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("LikePostManager", "Error syncing likes", databaseError.toException());
            }
        });
    }
}
