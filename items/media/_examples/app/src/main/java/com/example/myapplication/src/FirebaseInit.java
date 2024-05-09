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

import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;

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
        // 加载Post数据
        DatabaseReference postsRef = database.child("post");
        // 加载User数据
        DatabaseReference usersRef = database.child("user");
        // 先初始化User数据, 这样便于初始化user下面的ownPosts属性来表示这个用户发了多少帖子
        //''addListenerForSingleValueEvent'' will execute the onDataChange method   immediately and
        // after executing that method once, it stops listening to the reference location it is attached to.
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
                    String userType = snapshot.child("userType").getValue(String.class);
                    String userIndexInFirebase =  snapshot.child("userIndexInFirebase").getValue(String.class);

                    // 构造创建User对象
                    if (email != null && password != null && userId != null) {
                        User user = new User(userId, email, password, name, address, phone,userType,userIndexInFirebase);
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

        Log.d("InitialisePost", "Construction for Users complete");

        postsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("InitialisePost", "Initialisation===========Post===============");
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // 解析Post字段
                    String userID = snapshot.child("userID").getValue(String.class);
                    String postID = snapshot.child("postID").getValue(String.class);
                    String gender = snapshot.child("gender").getValue(String.class);
                    String masterCategory = snapshot.child("masterCategory").getValue(String.class);
                    String subCategory = snapshot.child("subCategory").getValue(String.class);
                    String articleType = snapshot.child("articleType").getValue(String.class);
                    String baseColour = snapshot.child("baseColour").getValue(String.class);
                    String season = snapshot.child("season").getValue(String.class);
//                    String year = snapshot.child("year").getValue(String.class);
                    //不知道为什么firebase上传之后这个year会变成Long类型，所以这里要处理一下
                    Object yearObj = snapshot.child("year").getValue();
                    String year;
                    if (yearObj instanceof Long) {
                        year = String.valueOf(yearObj);
                    } else if (yearObj instanceof String) {
                        year = (String) yearObj;
                    } else {
                        // 处理yearObj为null或其他类型的情况
                        year = "0";
                    }
                    String usage = snapshot.child("usage").getValue(String.class);
                    String productDisplayName = snapshot.child("productDisplayName").getValue(String.class);
                    String price = snapshot.child("price").getValue(String.class);
                    String status = snapshot.child("status").getValue(String.class);
                    String imageUrl = snapshot.child("image_url").getValue(String.class);
                    String description = snapshot.child("description").getValue(String.class);
                    String comments = snapshot.child("comments").getValue(String.class);
                    //我把comments改成了String类型，可能还得加个parse类型解析一下，然后发现comment类也暂时没用到了，因为firebase上就是一长串字符串储存的

                    String postIndexInFirebase = snapshot.child("postIndexInFirebase").getValue(String.class);

                    assert year != null;
                    assert price != null;

                    // 创建Tag对象
//                    Tag.ArticleType articleTypeObj = new Tag.ArticleType(articleType);
//                    Tag.SubCategory subCategoryObj = new Tag.SubCategory(subCategory, articleTypeObj);
//                    Tag.MasterCategory masterCategoryObj = new Tag.MasterCategory(masterCategory, subCategoryObj);
                    //Tag tag = new Tag(gender, masterCategory, subCategory, articleType, baseColour, season, Integer.parseInt(year), usage);

                    // 创建Post对象
                    Post post = new Post(postID,userID,gender,masterCategory,subCategory,articleType,baseColour,season,Integer.parseInt(year),usage,productDisplayName,Double.parseDouble(price),status,imageUrl,description,comments,postIndexInFirebase);

                    BPlusTreeManagerPost.getTreeInstance(FirebaseInit.this).insert(postID, post);
//                    User author = BPlusTreeManagerUser.getTreeInstance(FirebaseInit.this).query(userID).get(0);
//                    author.updateOwns(post);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("FirebaseInit", "Failed to load post data: " + databaseError.getMessage());
            }
        });


        // 此时post已经初始化完，但user的posts属性还没初始化。
        //////// 遍历post，用post里存的userID来找到那个user实例，然后把这个post add到this.posts列表中


    }
}