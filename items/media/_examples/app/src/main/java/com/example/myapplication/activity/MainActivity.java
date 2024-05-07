package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.activity.loginUsingBPlusTree.LoginActivityBPlusTree;
import com.example.myapplication.activity.Test.GlideActivityTest;
import com.google.firebase.FirebaseApp;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);
        Button BPlusTreeTest = findViewById(R.id.BPlusTreeTest);


        BPlusTreeTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivityBPlusTree.class);
                startActivity(intent);
            }
        });


        Button glideTestButton = findViewById(R.id.GlideTest);
        glideTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GlideActivityTest.class);
                startActivity(intent);
            }
        });



    }
}