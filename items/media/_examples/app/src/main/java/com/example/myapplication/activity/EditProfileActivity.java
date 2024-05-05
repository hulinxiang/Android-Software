package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.myapplication.R;
import com.example.myapplication.src.User;

public class EditProfileActivity extends AppCompatActivity {
    private ImageView profileImage;
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
    }

    private void init() {
        profileImage = findViewById(R.id.profile_image);
        passwordEditText = findViewById(R.id.edit_password);
        nameEditText = findViewById(R.id.edit_name);
        addressEditText = findViewById(R.id.edit_address);
        phoneEditText = findViewById(R.id.edit_phone);
        saveChangesButton = findViewById(R.id.btn_save_changes);
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

            // Save the updated user data
            saveUserData(user); // Implement this method to save the user data
        }
    }

    private User getCurrentUser() {
        // Implement this method to retrieve the current user's data
        // Return the current user object
        return null;
    }

    private void saveUserData(User user) {
        // Implement this method to save the user data
        // You can use SharedPreferences, SQLite, or any other storage mechanism
    }
}