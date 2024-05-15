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
        // 从本地存储中获取当前帖子的购买信息
        String buyIds = sharedPreferences.getString(postId, "");

        // 将当前用户的 ID 添加到购买信息中
        if (!buyIds.contains(userId)) {
            if (!buyIds.isEmpty()) {
                buyIds += ",";
            }
            buyIds += userId;

            // 从 Firebase 获取最新的购买信息
            String finalBuyIds = buyIds;
            Query query = postsRef.orderByChild("postID").equalTo(postId);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        String firebaseBuyIds = postSnapshot.child("buyIDs").getValue(String.class);
                        if (firebaseBuyIds != null) {
                            // 如果 Firebase 中存在购买信息,则将本地存储的购买信息与 Firebase 中的购买信息合并
                            String[] localIds = finalBuyIds.split(",");
                            String[] firebaseIds = firebaseBuyIds.split(",");
                            Set<String> mergedIds = new HashSet<>();
                            mergedIds.addAll(Arrays.asList(localIds));
                            mergedIds.addAll(Arrays.asList(firebaseIds));
                            String mergedBuyIds = TextUtils.join(",", mergedIds);

                            // 将更新后的购买信息存储到本地和 Firebase
                            sharedPreferences.edit().putString(postId, mergedBuyIds).apply();
                            postSnapshot.getRef().child("buyIDs").setValue(mergedBuyIds);
                        } else {
                            // 如果 Firebase 中不存在购买信息,则直接将本地的购买信息存储到 Firebase
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
                        // 如果 Firebase 中存在购买信息且本地存储中不存在,则将 Firebase 中的购买信息同步到本地存储
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