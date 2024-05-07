package com.example.myapplication.src.Firebase.UserManager;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.myapplication.src.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirebaseUserHelper {
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    public FirebaseUserHelper() {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("user");
    }

    public void addUser(User user) {
        Log.d("Firebase add operation", "Enter the method");

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("Firebase add operation", "Execute the method");
                // 这将给出"user"下子节点的数量
                long count = dataSnapshot.getChildrenCount();
                // 现在在这个新索引下设置新用户数据
                DatabaseReference newUserRef = myRef.child(String.valueOf(count));
                newUserRef.child("userID").setValue(user.getUserId());
                newUserRef.child("email").setValue(user.getEmail());
                newUserRef.child("password").setValue(user.getPasswordHash());
                newUserRef.child("name").setValue(user.getName());
                newUserRef.child("address").setValue(user.getAddress());
                newUserRef.child("phone").setValue(user.getPhone());
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Firebase add operation failed", "Failure to add user to firebase：" + databaseError.getCode());
            }
        });
    }


}
