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
 * @author Linxiang Hu u7633783
 * Helper class for Firebase remark management operations.
 * Provides methods to add, update, and delete remarks in the Firebase Realtime Database.
 */
public class FirebaseRemarkHelper {

    /**
     * Adds a new remark to the Firebase database under the "remark" node.
     * @param remarkDemo The RemarkDemo object containing the remark details to be stored.
     */
    public void addRemark(RemarkDemo remarkDemo) {
        // Get the singleton instance of FirebaseDatabase.
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        // Get a reference to the "remark" node.
        DatabaseReference myRef = database.getReference().child("remark");

        Log.d("Firebase add operation", "Enter the method");

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("Firebase add operation", "Execute the method");
                // Create a new reference for the new remark.
                // Set remark data in Firebase under the new node
                DatabaseReference newUserRef = myRef.child(remarkDemo.getIndex());
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


    /**
     * Updates an existing remark in the Firebase database.
     * @param remarkDemo The RemarkDemo object containing the updated details.
     */
    public void updateRemark(RemarkDemo remarkDemo) {
        // Get the singleton instance of FirebaseDatabase.
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        // Get a reference to the "remark" node.
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
                        // Update the remark's information in Firebase.
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

    /**
     * Deletes a remark from the Firebase database.
     * @param remarkDemo The RemarkDemo object that identifies the remark to be deleted.
     */
    public void deleteRemark(RemarkDemo remarkDemo) {
        // Get the singleton instance of FirebaseDatabase.
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        // Get a reference to the "remark" node.
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
                        newUserRef.removeValue();// Remove the remark from Firebase.
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