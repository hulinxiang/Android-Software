package com.example.myapplication.src;

import com.example.myapplication.BPlusTree.Post.BPlusTreeManagerPost;
import com.example.myapplication.BPlusTree.User.BPlusTreeManagerUser;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.app.Application;

import com.example.myapplication.src.Tag;

import android.util.Log;

public class FirebaseInit extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化Firebase
        FirebaseApp.initializeApp(this);
        loadDataFromFirebase();
    }


    private void loadDataFromFirebase() {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();

        // 加载User数据
        DatabaseReference usersRef = database.child("user");
        usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Log.d("InitialiseUser", "Initialisation===========User===============");

//                // 首先清除旧数据
//                BPlusTreeManager.getTreeInstance(FirebaseInitUser.this).clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // 解析每个字段
                    String userId = snapshot.child("userID").getValue(String.class);
                    String email = snapshot.child("email").getValue(String.class);
                    String password = snapshot.child("password").getValue(String.class);  // 保证这是原始密码
                    String name = snapshot.child("name").getValue(String.class);
                    String address = snapshot.child("address").getValue(String.class);
                    String phone = snapshot.child("phone").getValue(String.class);

                    // 构造创建User对象
                    if (email != null && password != null && userId != null) {
                        User user = new User(userId, email, password, name, address, phone);
                        BPlusTreeManagerUser.getTreeInstance(FirebaseInit.this).insert(email, user);
                        // Log.d("Constructing........", "Adding user: " + email + " to the local tree");
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("FirebaseInit", "Error loading data: " + databaseError.getMessage());
                System.err.println("Failed to load user data: " + databaseError.getMessage());
            }
        });

        // 加载Post数据
        DatabaseReference postsRef = database.child("post");

        postsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("InitialisePost", "Initialisation===========Post===============");
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // 解析Post字段
                    //需要先初始化ArticleType实例，
                    String articleTypeName = snapshot.child("articleType").getValue(String.class);
                    Tag.ArticleType ArticleType = new Tag.ArticleType(articleTypeName);

                    // 然后再初始化一个SubCategory实例
                    String subCategoryName = snapshot.child("subCategory").getValue(String.class);
                    Tag.SubCategory subCategory = new Tag.SubCategory(subCategoryName, ArticleType);

                    // ，然后再初始化MasterCategory实例，
                    String masterCategoryName = snapshot.child("masterCategory").getValue(String.class);
                    Tag.MasterCategory masterCategory = new Tag.MasterCategory(masterCategoryName, subCategory);

                    // 再初始化Tag，最后才有post实例
                    String gender = snapshot.child("gender").getValue(String.class);
                    String baseColour = snapshot.child("baseColour").getValue(String.class);
                    String season = snapshot.child("season").getValue(String.class);
                    String usage = snapshot.child("usage").getValue(String.class);
                    String year = snapshot.child("year").getValue(String.class);
                    assert year != null;
                    Tag tag = new Tag(gender, masterCategory, baseColour, season, Integer.parseInt(year), usage);


                    // 解析Post
                    String postID = snapshot.child("postID").getValue(String.class);
                    String description = snapshot.child("description").getValue(String.class);
                    String filename = snapshot.child("filename").getValue(String.class);
                    String link = snapshot.child("link").getValue(String.class);
                    String price = snapshot.child("price").getValue(String.class);
                    String productDisplayName = snapshot.child("productDisplayName").getValue(String.class);
                    String status = snapshot.child("status").getValue(String.class);
                    String comments = snapshot.child("comment").getValue(String.class);
                    assert price != null;
                    Post post = new Post(postID, tag, productDisplayName, Double.parseDouble(price), status, description, filename, link, comments);
                    BPlusTreeManagerPost.getTreeInstance(FirebaseInit.this).insert(postID, post);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("FirebaseInit", "Failed to load post data: " + databaseError.getMessage());
            }
        });
    }
}