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

public class LikePostManager {
    private SharedPreferences sharedPreferences;
    private DatabaseReference postsRef;

    public LikePostManager(Context context) {
        sharedPreferences = context.getSharedPreferences("like_preferences", Context.MODE_PRIVATE);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        postsRef = database.getReference("post");
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
            Query query = postsRef.orderByChild("postID").equalTo(postId);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        String firebaseLikeIds = postSnapshot.child("likeIDs").getValue(String.class);
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
                            postSnapshot.getRef().child("likeIDs").setValue(mergedLikeIds);
                        } else {
                            // 如果 Firebase 中不存在点赞信息,则直接将本地的点赞信息存储到 Firebase
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
        // 从本地存储中获取当前帖子的点赞信息
        String likeIds = sharedPreferences.getString(postId, "");

        // 将当前用户的 ID 从点赞信息中移除
        if (likeIds.contains(userId)) {
            String[] ids = likeIds.split(",");
            Set<String> idSet = new HashSet<>(Arrays.asList(ids));
            idSet.remove(userId);
            String updatedLikeIds = TextUtils.join(",", idSet);

            // 从 Firebase 获取最新的点赞信息
            Query query = postsRef.orderByChild("postID").equalTo(postId);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        String firebaseLikeIds = postSnapshot.child("likeIDs").getValue(String.class);
                        if (firebaseLikeIds != null) {
                            // 将 Firebase 中的点赞信息与本地更新后的点赞信息合并
                            Set<String> firebaseIdSet = new HashSet<>(Arrays.asList(firebaseLikeIds.split(",")));
                            Set<String> localIdSet = new HashSet<>(Arrays.asList(updatedLikeIds.split(",")));
                            firebaseIdSet.removeAll(localIdSet);
                            String mergedLikeIds = TextUtils.join(",", firebaseIdSet);

                            // 将合并后的点赞信息写入 Firebase
                            postSnapshot.getRef().child("likeIDs").setValue(mergedLikeIds);
                        }
                    }
                    // 更新本地存储的点赞信息
                    sharedPreferences.edit().putString(postId, updatedLikeIds).apply();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.e("LikePostManager", "Error getting likes from Firebase", databaseError.toException());
                }
            });
        }
    }
//    public void syncLikes(String postId) {
//        Query query = postsRef.orderByChild("postID").equalTo(postId);
//        query.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//                    String likeIDsFromFirebase = postSnapshot.child("likeIDs").getValue(String.class);
//                    if (likeIDsFromFirebase == null) {
//                        // 如果 Firebase 中没有点赞信息,则将其视为空字符串
//                        likeIDsFromFirebase = "";
//                    }
//                    // 将从 Firebase 获取的点赞信息存储到本地
//                    sharedPreferences.edit().putString(postId, likeIDsFromFirebase).apply();
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Log.e("LikePostManager", "Error syncing likes", databaseError.toException());
//            }
//        });
//    }
    public void syncLikes(String postId) {
        Query query = postsRef.orderByChild("postID").equalTo(postId);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String likeIDsFromFirebase = postSnapshot.child("likeIDs").getValue(String.class);
                    String localLikeIDs = sharedPreferences.getString(postId, "");

                    if (likeIDsFromFirebase != null && !localLikeIDs.contains(likeIDsFromFirebase)) {
                        // 如果 Firebase 中存在点赞信息且本地存储中不存在,则将 Firebase 中的点赞信息同步到本地存储
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
