package com.example.myapplication.src.Firebase.UserManager;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.myapplication.src.User;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class FirebaseUserHelper {


    public void addUser(User user) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("user");


        Log.d("Firebase add operation", "Enter the method");

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
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
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Firebase add operation failed", "Failure to add user to firebase：" + databaseError.getCode());
            }
        });
    }


    public void updateUser(User user) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("user");
        Log.d("Firebase update operation", "Enter the method");
        String curUserId = user.getUserId();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("Firebase update operation", "Execute the method");
                int count = 0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (Objects.equals(snapshot.child("userID").getValue(String.class), curUserId)) {
                        DatabaseReference newUserRef = myRef.child(String.valueOf(count));
                        newUserRef.child("email").setValue(user.getEmail());
                        newUserRef.child("password").setValue(user.getPasswordHash());
                        newUserRef.child("name").setValue(user.getName());
                        newUserRef.child("address").setValue(user.getAddress());
                        newUserRef.child("phone").setValue(user.getPhone());
                        break;
                    }
                    count++;
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Firebase update operation failed", "Failure to update user to firebase：" + databaseError.getCode());
            }
        });
    }

    public void deleteUser(User user) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("user");
        Log.d("Firebase delete operation", "Enter the method");
        String curUserId = user.getUserId();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("Firebase delete operation", "Execute the method");
                int count = 0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (Objects.equals(snapshot.child("userID").getValue(String.class), curUserId)) {
                        DatabaseReference newUserRef = myRef.child(String.valueOf(count));
                        newUserRef.removeValue();
                        break;
                    }
                    count++;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Firebase delete operation failed", "Failure to delete user to firebase：" + databaseError.getCode());
            }
        });
    }


}
