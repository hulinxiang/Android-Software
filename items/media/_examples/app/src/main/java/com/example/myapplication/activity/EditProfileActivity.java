package com.example.myapplication.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.BPlusTree.User.BPlusTreeManagerUser;
import com.example.myapplication.R;
import com.example.myapplication.src.Firebase.UserManager.FirebaseUserManager;
import com.example.myapplication.src.SessionManager;
import com.example.myapplication.src.User;

public class EditProfileActivity extends AppCompatActivity {
    private ImageView returnButton;
    private EditText passwordEditText;
    private EditText nameEditText;
    private EditText addressEditText;
    private EditText phoneEditText;
    private Button saveChangesButton;

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

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    /**
     * Initialize views.
     */
    private void init() {
        returnButton = findViewById(R.id.returnButton);
        passwordEditText = findViewById(R.id.edit_password);
        nameEditText = findViewById(R.id.edit_name);
        addressEditText = findViewById(R.id.edit_address);
        phoneEditText = findViewById(R.id.edit_phone);
        saveChangesButton = findViewById(R.id.btn_save_changes);
    }

    /**
     * Load the current user data from the local BPlusTree.
     */
    private void loadUserData() {
        user = getCurrentUser();
        if (user != null) {
            nameEditText.setText(user.getName());
            addressEditText.setText(user.getAddress());
            phoneEditText.setText(user.getPhone());
        } else {
            Toast.makeText(EditProfileActivity.this, "User data not found!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Save changes made to the user profile to both Firebase and local BPlusTree.
     */
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

            // Update Firebase
            FirebaseUserManager.getInstance(this).updateUser(user);
            // Update local BPlusTree
            BPlusTreeManagerUser.getTreeInstance(this).insert(user.getEmail(), user);

            Toast.makeText(EditProfileActivity.this, "Profile updated successfully!", Toast.LENGTH_SHORT).show();
            finish(); // Exit activity after successful save
        } else {
            Toast.makeText(this, "User data not found!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Retrieve the current user from the local BPlusTree.
     *
     * @return User object representing the current user.
     */
    private User getCurrentUser() {
        User currentUser = SessionManager.getInstance().getUser();
        if (currentUser == null) {
            Toast.makeText(this, "No current user found in session!", Toast.LENGTH_SHORT).show();
            return null;
        }

        String currentUserId = currentUser.getUserId();

        // Retrieve from local BPlusTree using userId
        User user = BPlusTreeManagerUser.getUserViaUserId(this, currentUserId);
        if (user == null) {
            Toast.makeText(this, "User data not found!", Toast.LENGTH_SHORT).show();
        }

        return user;
    }
}
