package com.example.myapplication.src;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LikePostManager {
    private static final String LIKE_IDS_PREF_KEY = "like_ids_pref_key";
    private SharedPreferences sharedPreferences;
    private DatabaseReference postsRef;

    public LikePostManager(Context context) {
        sharedPreferences = context.getSharedPreferences("like_preferences", Context.MODE_PRIVATE);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        postsRef = database.getReference("Post");
    }

    public void likePost(String postId, String userId) {
        // 从本地存储中获取当前帖子的点赞信息
        String likeIds = sharedPreferences.getString(postId, "");

        // 将当前用户的 ID 添加到点赞信息中
        if (!likeIds.contains(userId)) {
            if (!likeIds.isEmpty()) {
                likeIds += ",";
            }
            likeIds += userId;

            // 从 Firebase 获取最新的点赞信息
            String finalLikeIds = likeIds;
            postsRef.child(postId).child("likeIDs").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String firebaseLikeIds = dataSnapshot.getValue(String.class);
                    if (firebaseLikeIds != null) {
                        // 如果 Firebase 中存在点赞信息,则将本地存储的点赞信息与 Firebase 中的点赞信息合并
                        String[] localIds = finalLikeIds.split(",");
                        String[] firebaseIds = firebaseLikeIds.split(",");
                        Set<String> mergedIds = new HashSet<>();
                        mergedIds.addAll(Arrays.asList(localIds));
                        mergedIds.addAll(Arrays.asList(firebaseIds));
                        String mergedLikeIds = TextUtils.join(",", mergedIds);

                        // 将更新后的点赞信息存储到本地和 Firebase
                        sharedPreferences.edit().putString(postId, mergedLikeIds).apply();
                        postsRef.child(postId).child("likeIDs").setValue(mergedLikeIds);
                    } else {
                        // 如果 Firebase 中不存在点赞信息,则直接将本地的点赞信息存储到 Firebase
                        sharedPreferences.edit().putString(postId, finalLikeIds).apply();
                        postsRef.child(postId).child("likeIDs").setValue(finalLikeIds);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.e("LikePostManager", "Error getting likes from Firebase", databaseError.toException());
                }
            });
        }
    }

    public void syncLikes(String postId) {
        postsRef.child(postId).child("likeIDs").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String likeIDsFromFirebase = dataSnapshot.getValue(String.class);
                if (likeIDsFromFirebase == null) {
                    // 如果 Firebase 中没有点赞信息,则将其视为空字符串
                    likeIDsFromFirebase = "";
                }
                // 将从 Firebase 获取的点赞信息存储到本地
                sharedPreferences.edit().putString(postId, likeIDsFromFirebase).apply();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("LikePostManager", "Error syncing likes", databaseError.toException());
            }
        });
    }
}

