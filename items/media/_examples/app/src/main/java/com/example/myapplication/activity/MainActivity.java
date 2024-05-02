package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.LoginActivityTest;
import com.example.myapplication.R;
import com.example.myapplication.activity.loginUsingBPlusTree.LoginActivityBPlusTree;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button firebaseTest = findViewById(R.id.firebaseTest);
        Button BPlusTreeTest = findViewById(R.id.BPlusTreeTest);

        firebaseTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivityTest.class);
                startActivity(intent);
            }
        });

        BPlusTreeTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivityBPlusTree.class);
                startActivity(intent);
            }
        });


    }
}