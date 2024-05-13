package com.example.myapplication.src;

import com.example.myapplication.BPlusTree.Post.BPlusTreeManagerPost;
import com.example.myapplication.BPlusTree.Remark.BPlusTreeManagerRemark;
import com.example.myapplication.BPlusTree.User.BPlusTreeManagerUser;
import com.example.myapplication.src.Remark.AnonymousRemarkFactory;
import com.example.myapplication.src.Remark.AnonymousRemarkFactoryManager;
import com.example.myapplication.src.Remark.CommonRemarkFactoryManager;
import com.example.myapplication.src.Remark.RemarkDemo;
import com.example.myapplication.src.Remark.RemarkFactory;
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

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

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

        DatabaseReference remarkRef = database.child("remark");
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
                    String userIndexInFirebase = snapshot.child("userIndexInFirebase").getValue(String.class);

                    // 构造创建User对象
                    if (email != null && password != null && userId != null) {
                        User user = new User(userId, email, password, name, address, phone, userType, userIndexInFirebase);
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
                    String userID = snapshot.child("UserID").getValue(String.class);//这里不小心改了下云端字段，我的错
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
//                    assert userID != null;

                    // 创建Tag对象
//                    Tag.ArticleType articleTypeObj = new Tag.ArticleType(articleType);
//                    Tag.SubCategory subCategoryObj = new Tag.SubCategory(subCategory, articleTypeObj);
//                    Tag.MasterCategory masterCategoryObj = new Tag.MasterCategory(masterCategory, subCategoryObj);
                    //Tag tag = new Tag(gender, masterCategory, subCategory, articleType, baseColour, season, Integer.parseInt(year), usage);

                    // 创建Post对象
                    Post post = new Post(postID, userID, gender, masterCategory, subCategory, articleType, baseColour, season, Integer.parseInt(year), usage, productDisplayName, Double.parseDouble(price), status, imageUrl, description, comments, postIndexInFirebase);

                    BPlusTreeManagerPost.getTreeInstance(FirebaseInit.this).insert(postID, post);
                    User author = BPlusTreeManagerUser.getTreeInstance(FirebaseInit.this).query(userID).get(0);
                    author.updateOwns(post);//这里虽然写的是update实际上是第一次初始化时把post加到了ownPosts里

//这段可以用来检查，先留一下，然后如果要加likelist那种多的应该也能参考
//                    if (userID != null) {
//                        List<User> allUsers = BPlusTreeManagerUser.getTreeInstance(FirebaseInit.this).queryAllData();
//                        if (!allUsers.isEmpty()) {
//                            User author = null;
//                            for (User user : allUsers) {
//                                if (user.getEmail().equals(userID)) {
//                                    author = user;
//                                    break;
//                                }
//                            }
//                            if (author != null) {
//                                author.updateOwns(post);
//                                Log.d("Add PostList", "User found for userID: " + userID);
//                                // 更新 B+ 树中的用户信息
//                                // BPlusTreeManagerUser.getTreeInstance(FirebaseInit.this).update(author);
//                            } else {
//                                Log.e("Add PostList", "User not found for userID: " + userID);
//                            }
//                        } else {
//                            Log.e("Add PostList", "No users found in the B+ tree");
//                        }
//                    } else {
//                        Log.e("Add PostList", "userID is null");
//                    }


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("FirebaseInit", "Failed to load post data: " + databaseError.getMessage());
            }
        });


        // 此时post已经初始化完，但user的posts属性还没初始化。
        //////// 遍历post，用post里存的userID来找到那个user实例，然后把这个post add到this.posts列表中


        remarkRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot shot : snapshot.getChildren()) {
                    String index = shot.child("Index").getValue(String.class);
                    String postID = shot.child("PostID").getValue(String.class);
                    String remark = shot.child("Remark").getValue(String.class);
                    String userEmail = shot.child("UserEmail").getValue(String.class);
                    RemarkDemo remarkDemo = null;
                    if ("Anonymous User".equals(userEmail)) {
                        remarkDemo = AnonymousRemarkFactoryManager.getInstance().createWithIndex(remark, userEmail, postID, index);
                    } else {
                        remarkDemo = CommonRemarkFactoryManager.getInstance().createWithIndex(remark, userEmail, postID, index);
                    }
                    List<List<RemarkDemo>> list = BPlusTreeManagerRemark.getTreeInstance(getApplicationContext()).query(postID);
                    if (list.isEmpty()) {
                        List<RemarkDemo> value = new ArrayList<>();
                        value.add(remarkDemo);
                        BPlusTreeManagerRemark.getTreeInstance(getApplicationContext()).insert(postID, value);
                    } else {
                        list.get(0).add(remarkDemo);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("FirebaseInit", "Failed to load remark data: " + error.getMessage());
            }
        });

    }
}