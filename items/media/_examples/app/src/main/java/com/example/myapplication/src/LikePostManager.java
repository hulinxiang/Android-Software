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
 * @author Yingxuan Tang u7670526
 *
 * The LikePostManager class handles liking and unliking posts in the application.
 */
public class LikePostManager {
    private SharedPreferences sharedPreferences;
    private DatabaseReference postsRef;

    /**
     * Constructor for LikePostManager.
     *
     * @param context The context of the application.
     */
    public LikePostManager(Context context) {
        sharedPreferences = context.getSharedPreferences("like_preferences", Context.MODE_PRIVATE);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        postsRef = database.getReference("post");
    }

    /**
     * A user likes a post.
     *
     * @param postId The ID of the post to like.
     * @param userId The ID of the user liking the post.
     */
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


    /**
     * Synchronizes likes for a post between local storage and Firebase.
     *
     * @param postId The ID of the post to synchronize likes for.
     */
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
