package com.example.myapplication.src.Firebase.UserManager;
import android.util.Log;
import androidx.annotation.NonNull;
import com.example.myapplication.src.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.Objects;


/**
 * @author Linxiang Hu u7633783, Yingxuan Tang u7670526
 * A helper class for performing user management operations in Firebase Realtime Database.
 * This class provides methods to add and update user data in a Firebase database.
 */
public class FirebaseUserHelper {

    /**
     * Adds a user to the Firebase database under the "user" node.
     * This method will count the existing children under the "user" node and add the new user at the next index.
     * @param user The user object to add to the database.
     */
    public void addUser(User user) {
        // Get the singleton instance of FirebaseDatabase.
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        // Get a reference to the "user" node.
        DatabaseReference myRef = database.getReference().child("user");

        // Attach a single event listener to read data once.
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Get the number of children under "user".
                long count = dataSnapshot.getChildrenCount();
                // Now set the new user data under this new index
                DatabaseReference newUserRef = myRef.child(String.valueOf(count));
                newUserRef.child("userID").setValue(user.getUserId());
                newUserRef.child("email").setValue(user.getEmail());
                newUserRef.child("password").setValue(user.getPasswordHash());
                newUserRef.child("name").setValue(user.getName());
                newUserRef.child("address").setValue(user.getAddress());
                newUserRef.child("phone").setValue(user.getPhone());
                newUserRef.child("userIndexInFirebase").setValue(user.getUserIndexInFirebase());
                newUserRef.child("userType").setValue(user.getUserType());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Firebase add operation failed", "Failure to add user to firebase：" + databaseError.getCode());
            }
        });
    }

    /**
     * Updates an existing user's data in the Firebase database.
     * This method locates the user by userId and then updates their information.
     * @param user The user object containing updated information.
     */
    public void updateUser(User user) {
        // Get the singleton instance of FirebaseDatabase.
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        // Get a reference to the "user" node.
        DatabaseReference myRef = database.getReference().child("user");
        Log.d("Firebase update operation", "Enter the method");
        // Get the current user's ID.
        String curUserId = user.getUserId();
        // Attach a single event listener to read data once.
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("Firebase update operation", "Execute the method");
                int count = 0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (Objects.equals(snapshot.child("userID").getValue(String.class), curUserId)) {
                        DatabaseReference newUserRef = myRef.child(String.valueOf(count));
                        // Update the user's information in Firebase.
                        newUserRef.child("email").setValue(user.getEmail());
                        newUserRef.child("password").setValue(user.getPasswordHash());
                        newUserRef.child("name").setValue(user.getName());
                        newUserRef.child("address").setValue(user.getAddress());
                        newUserRef.child("phone").setValue(user.getPhone());
                        newUserRef.child("userIndexInFirebase").setValue(user.getUserIndexInFirebase());
                        newUserRef.child("userType").setValue(user.getUserType());
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
}