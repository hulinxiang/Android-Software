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
 * The BuyPostManager class manages the purchase information of posts. It interacts with both local storage (SharedPreferences) and Firebase Realtime Database.
 */
public class BuyPostManager {
    private SharedPreferences sharedPreferences;
    private DatabaseReference postsRef;

    /**
     * Constructor for BuyPostManager.
     *
     * @param context The context of the application.
     *
     * Initializes the BuyPostManager with the provided context.
     */
    public BuyPostManager(Context context) {
        sharedPreferences = context.getSharedPreferences("buy_preferences", Context.MODE_PRIVATE);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        postsRef = database.getReference("post");
    }

    /**
     * Manages the purchase of a post.
     *
     * @param postId The ID of the post being purchased.
     * @param userId The ID of the user making the purchase.
     *
     * Checks if the user has already purchased the post, and updates the purchase information locally and in Firebase if necessary.
     */
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

    /**
     * Synchronizes the purchase information of a post between local storage and Firebase.
     *
     * @param postId The ID of the post to synchronize purchase information for.
     *
     * Checks for any discrepancies in purchase information between local storage and Firebase, and updates local storage if necessary.
     */
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