package com.example.myapplication.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.BPlusTree.User.BPlusTreeManagerUser;
import com.example.myapplication.R;
import com.example.myapplication.src.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class EditProfileActivity extends AppCompatActivity {
    private ImageView profileImage;
    private EditText passwordEditText;
    private EditText nameEditText;
    private EditText addressEditText;
    private EditText phoneEditText;
    private Button saveChangesButton;

    private DatabaseReference userDatabaseRef;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        init();
        loadUserData();

        saveChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveProfileChanges();
            }
        });
    }

    private void init() {
        profileImage = findViewById(R.id.profile_image);
        passwordEditText = findViewById(R.id.edit_password);
        nameEditText = findViewById(R.id.edit_name);
        addressEditText = findViewById(R.id.edit_address);
        phoneEditText = findViewById(R.id.edit_phone);
        saveChangesButton = findViewById(R.id.btn_save_changes);
        userDatabaseRef = FirebaseDatabase.getInstance().getReference("user");
    }

    private void loadUserData() {
        // Retrieve the current user's data and populate the fields
        user = getCurrentUser(); // Implement this method to get the current user

        if (user != null) {
            nameEditText.setText(user.getName());
            addressEditText.setText(user.getAddress());
            phoneEditText.setText(user.getPhone());
        }
    }

    private void saveProfileChanges() {
        if (user != null) {
            String newPassword = passwordEditText.getText().toString().trim();
            String newName = nameEditText.getText().toString().trim();
            String newAddress = addressEditText.getText().toString().trim();
            String newPhone = phoneEditText.getText().toString().trim();

            // Update the user's information
            if (!newPassword.isEmpty()) {
                user.updatePassword(newPassword);
            }
            user.updateName(newName);
            user.updateAddress(newAddress);
            user.updatePhone(newPhone);

            // Save the updated user data to Firebase
            userDatabaseRef.child(user.getUserId()).setValue(user)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(EditProfileActivity.this, "Profile updated successfully!", Toast.LENGTH_SHORT).show();
                            finish(); // Exit activity after successful save
                        } else {
                            Toast.makeText(EditProfileActivity.this, "Failed to update profile!", Toast.LENGTH_SHORT).show();
                        }
                    });

            // Save the updated user data to local BPlusTree
            BPlusTreeManagerUser.getTreeInstance(this).insert(user.getEmail(), user);
        }
    }

    private User getCurrentUser() {
        final String currentUserEmail = "comp6442@anu.edu.au"; // Replace with the actual user's email

        final User[] userHolder = new User[1];
        userDatabaseRef.orderByChild("email").equalTo(currentUserEmail)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            userHolder[0] = snapshot.getValue(User.class);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(EditProfileActivity.this, "Failed to load user data: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        // Simulate user retrieval from local BPlusTree
        List<User> matchingUsers = BPlusTreeManagerUser.getTreeInstance(this).query(currentUserEmail);
        if (!matchingUsers.isEmpty()) {
            userHolder[0] = matchingUsers.get(0);
        }

        return userHolder[0];
    }
}
