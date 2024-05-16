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

public class BuyPostManager {
    private SharedPreferences sharedPreferences;
    private DatabaseReference postsRef;

    public BuyPostManager(Context context) {
        sharedPreferences = context.getSharedPreferences("buy_preferences", Context.MODE_PRIVATE);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        postsRef = database.getReference("post");
    }

    public void buyPost(String postId, String userId) {
        // Get the purchase information of the current post from local storage
        String buyIds = sharedPreferences.getString(postId, "");

        // Add the current user's ID to the purchase information
        if (!buyIds.contains(userId)) {
            if (!buyIds.isEmpty()) {
                buyIds += ",";
            }
            buyIds += userId;

            // Get the latest purchase information from Firebase
            String finalBuyIds = buyIds;
            Query query = postsRef.orderByChild("postID").equalTo(postId);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        String firebaseBuyIds = postSnapshot.child("buyIDs").getValue(String.class);
                        if (firebaseBuyIds != null) {
                            // If purchase information exists in Firebase, merge the purchase information from local storage with the purchase information from Firebase
                            String[] localIds = finalBuyIds.split(",");
                            String[] firebaseIds = firebaseBuyIds.split(",");
                            Set<String> mergedIds = new HashSet<>();
                            mergedIds.addAll(Arrays.asList(localIds));
                            mergedIds.addAll(Arrays.asList(firebaseIds));
                            String mergedBuyIds = TextUtils.join(",", mergedIds);

                            // Store the updated purchase information locally and in Firebase
                            sharedPreferences.edit().putString(postId, mergedBuyIds).apply();
                            postSnapshot.getRef().child("buyIDs").setValue(mergedBuyIds);
                        } else {
                            // If purchase information does not exist in Firebase, directly store the local purchase information in Firebase
                            sharedPreferences.edit().putString(postId, finalBuyIds).apply();
                            postSnapshot.getRef().child("buyIDs").setValue(finalBuyIds);
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.e("BuyPostManager", "Error getting buys from Firebase", databaseError.toException());
                }
            });
        }
    }

    public void syncBuys(String postId) {
        Query query = postsRef.orderByChild("postID").equalTo(postId);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String buyIDsFromFirebase = postSnapshot.child("buyIDs").getValue(String.class);
                    String localBuyIDs = sharedPreferences.getString(postId, "");

                    if (buyIDsFromFirebase != null && !localBuyIDs.contains(buyIDsFromFirebase)) {
                        // If purchase information exists in Firebase and does not exist in local storage, synchronize the purchase information from Firebase to local storage
                        sharedPreferences.edit().putString(postId, buyIDsFromFirebase).apply();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("BuyPostManager", "Error syncing buys", databaseError.toException());
            }
        });
    }
}