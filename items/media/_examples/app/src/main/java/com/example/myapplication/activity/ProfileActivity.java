package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.myapplication.R;

public class ProfileActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private LinearLayout btnHome, btnSearch, btnCreate, btnInbox, btnProfile;
    private CardView cardView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Initialize views
        toolbar = findViewById(R.id.toolbar);
        btnHome = findViewById(R.id.btn_home);
        btnSearch = findViewById(R.id.btn_search);
        btnCreate = findViewById(R.id.btn_create);
        btnInbox = findViewById(R.id.btn_inbox);
        btnProfile = findViewById(R.id.btn_profile);
        cardView1 = findViewById(R.id.card_view_1);

        // Set up toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Profile");

        // Set click listeners for bottom navigation
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to home activity
                Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to search activity
                Intent intent = new Intent(ProfileActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to create activity
                Intent intent = new Intent(ProfileActivity.this, CreateActivity.class);
                startActivity(intent);
            }
        });

        btnInbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to inbox activity
                Intent intent = new Intent(ProfileActivity.this, InboxActivity.class);
                startActivity(intent);
            }
        });

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to profile activity
                Intent intent = new Intent(ProfileActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        // Set click listener for card view 1
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to post details activity for card view 1
                Intent intent = new Intent(ProfileActivity.this, PostActivity.class);
                intent.putExtra("postId", "1");
                startActivity(intent);
            }
        });

        // Add click listeners for other card views as needed
    }
}