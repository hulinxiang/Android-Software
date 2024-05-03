package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.myapplication.R;

public class EditProfileActivity extends AppCompatActivity {
    private ImageView profileImage;
    private Button changePhotoButton;
    private EditText nameEditText;
    private EditText emailEditText;
    private EditText bioEditText;
    private Button saveChangesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        init();

        changePhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle changing profile photo logic here
            }
        });

        saveChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle saving profile changes logic here
            }
        });
    }

    private void init() {
        profileImage = findViewById(R.id.profile_image);
        changePhotoButton = findViewById(R.id.btn_change_photo);
        nameEditText = findViewById(R.id.edit_name);
        emailEditText = findViewById(R.id.edit_email);
        bioEditText = findViewById(R.id.edit_bio);
        saveChangesButton = findViewById(R.id.btn_save_changes);
    }
}