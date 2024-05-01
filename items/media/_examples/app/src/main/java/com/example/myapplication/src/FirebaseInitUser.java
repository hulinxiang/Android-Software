package com.example.myapplication.src;
import com.example.myapplication.BPlusTree.User.BPlusTreeManager;
import com.example.myapplication.activity.loginUsingBPlusTree.RegisterActivityBPlusTree;
import com.example.myapplication.entity.LoginNameBean;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import android.app.Application;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import com.example.myapplication.BPlusTree.User.BPlusTree;
import com.example.myapplication.BPlusTree.User.BPlusTreeManager;

public class FirebaseInitUser extends Application{
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

                        // 在BPlusTree中插入用户数据
                        LoginNameBean user = new LoginNameBean(name, password);
                        BPlusTreeManager.getTreeInstance(FirebaseInitUser.this).insert(name, user);
                        // User user = new User(userId, email, password, name, address, phone);
                        // BPlusTreeManager.getTreeInstance(FirebaseInitUser.this).insert(user.getUserId(), user);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.err.println("Failed to load user data: " + databaseError.getMessage());
            }
        });

    }
}
