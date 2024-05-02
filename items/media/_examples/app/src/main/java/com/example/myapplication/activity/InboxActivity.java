package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.view.View;
import android.widget.LinearLayout;

import com.example.myapplication.R;

public class InboxActivity extends AppCompatActivity {
    private LinearLayout home;
    private LinearLayout search;
    private LinearLayout create;
    private LinearLayout inbox;
    private LinearLayout profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);

        init();
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InboxActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InboxActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InboxActivity.this,CreateActivity.class);
                startActivity(intent);
            }
        });

        inbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InboxActivity.this,InboxActivity.class);
                startActivity(intent);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InboxActivity.this,ProfileActivity.class);
                startActivity(intent);
            }
        });
    }
    private void init(){
        home = findViewById(R.id.btn_home);
        search = findViewById(R.id.btn_search);
        create = findViewById(R.id.btn_create);
        inbox = findViewById(R.id.btn_inbox);
        profile = findViewById(R.id.btn_profile);
    }
}