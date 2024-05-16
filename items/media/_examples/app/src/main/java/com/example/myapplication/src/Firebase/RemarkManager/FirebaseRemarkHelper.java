package com.example.myapplication.src.Firebase.RemarkManager;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.myapplication.src.Remark.RemarkDemo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

/**
 * @author Hu
 */
public class FirebaseRemarkHelper {

    public void addRemark(RemarkDemo remarkDemo) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("remark");

        Log.d("Firebase add operation", "Enter the method");

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("Firebase add operation", "Execute the method");
                // This will give the number of "remark" subnodes
                long count = dataSnapshot.getChildrenCount();
                // Now set the new remark data under this new index
                DatabaseReference newUserRef = myRef.child(String.valueOf(count));
                newUserRef.child("Index").setValue(remarkDemo.getIndex());
                newUserRef.child("PostID").setValue(remarkDemo.getPostId());
                newUserRef.child("Remark").setValue(remarkDemo.getText());
                newUserRef.child("UserEmail").setValue(remarkDemo.getUserEmail());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Firebase add operation failed", "Failure to add user to firebase：" + databaseError.getCode());
            }
        });
    }


    public void updateRemark(RemarkDemo remarkDemo) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("remark");
        Log.d("Firebase update operation", "Enter the method");
        String curRemarkId = remarkDemo.getIndex();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("Firebase update operation", "Execute the method");
                int count = 0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (Objects.equals(snapshot.child("Index").getValue(String.class), curRemarkId)) {
                        DatabaseReference newUserRef = myRef.child(String.valueOf(count));
                        newUserRef.child("Index").setValue(remarkDemo.getIndex());
                        newUserRef.child("PostID").setValue(remarkDemo.getPostId());
                        newUserRef.child("Remark").setValue(remarkDemo.getText());
                        newUserRef.child("UserEmail").setValue(remarkDemo.getUserEmail());
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

    public void deleteRemark(RemarkDemo remarkDemo) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("remark");
        Log.d("Firebase delete operation", "Enter the method");
        String curRemarkId = remarkDemo.getIndex();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("Firebase delete operation", "Execute the method");
                int count = 0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (Objects.equals(snapshot.child("Index").getValue(String.class), curRemarkId)) {
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