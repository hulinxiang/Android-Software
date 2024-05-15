package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.activity.loginUsingBPlusTree.LoginActivityBPlusTree;
import com.google.firebase.FirebaseApp;

/**
 * This is the main activity of the application.
 * It displays the main screen with a button to start the BPlusTreeTest activity.
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firebase
        FirebaseApp.initializeApp(this);

        // Get the button from the layout
        Button BPlusTreeTest = findViewById(R.id.getStartedButton);

        // Set click listener for the button
        BPlusTreeTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the LoginActivityBPlusTree activity when the button is clicked
                Intent intent = new Intent(MainActivity.this, LoginActivityBPlusTree.class);
                startActivity(intent);
            }
        });
    }
}