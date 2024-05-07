package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.src.SessionManager;
import com.example.myapplication.src.User;

public class ProfileActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private LinearLayout home;
    private LinearLayout search;
    private LinearLayout create;
    private LinearLayout inbox;
    private LinearLayout profile;
    private CardView cardView1;


    private TextView text_name;
    private TextView text_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        init();

        // Initialize views
        toolbar = findViewById(R.id.toolbar);
        cardView1 = findViewById(R.id.card_view_1);

        // Set up toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Profile");

        //user profile display
        User currentUser = SessionManager.getInstance().getUser();
        if (currentUser != null) {
            // Use user data as needed
            String email = currentUser.getEmail();
            text_email.setText(email);

        }

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this,CreateActivity.class);
                startActivity(intent);
            }
        });

        inbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this,InboxActivity.class);
                startActivity(intent);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this,ProfileActivity.class);
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
    private void init(){
        home = findViewById(R.id.btn_home);
        search = findViewById(R.id.btn_search);
        create = findViewById(R.id.btn_create);
        inbox = findViewById(R.id.btn_inbox);
        profile = findViewById(R.id.btn_profile);
        text_email = findViewById(R.id.text_email);
        text_name = findViewById(R.id.text_name);
    }

}