package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.BPlusTree.Post.BPlusTreeManagerPost;
import com.example.myapplication.R;
import com.example.myapplication.src.Post;

import java.util.List;

import com.example.myapplication.activity.Image.GlideImageLoader;
public class HomeActivity extends AppCompatActivity {
    private LinearLayout home;
    private LinearLayout search;
    private LinearLayout create;
    private LinearLayout inbox;
    private LinearLayout profile;
    private GridLayout gl_post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();

        // Set click listeners for bottom navigation
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,CreateActivity.class);
                startActivity(intent);
            }
        });

        inbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,InboxActivity.class);
                startActivity(intent);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,ProfileActivity.class);
                startActivity(intent);
            }
        });

        // Initialize the grid layout for posts
        gl_post = findViewById(R.id.gl_home);
        // Show posts in the grid layout
        showPost();

    }

    /**
     * Show posts in the grid layout.
     */
    private void showPost(){
        // Get a list of posts using a B+ tree
        List<Post> list = BPlusTreeManagerPost.randomRecommender(getApplicationContext());

        for (Post post: list){
            // Inflate the layout for each post
            View view = LayoutInflater.from(this).inflate(R.layout.item_card,null);
            ImageView card_image = view.findViewById(R.id.card_image);
            TextView card_name = view.findViewById(R.id.card_name);
            TextView card_price = view.findViewById(R.id.card_price);

            // Load the post image using Glide
            GlideImageLoader.loadImage(HomeActivity.this,post.getImageUrl(),card_image);
            card_name.setText(post.getProductDisplayName());
            card_price.setText(String.valueOf(post.getPrice()));

            // Get the screen width and set the layout params
            int screenWidth = getResources().getDisplayMetrics().widthPixels;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(screenWidth/2, ViewGroup.LayoutParams.WRAP_CONTENT);
            // Add the view to the grid layout
            gl_post.addView(view,params);

            // Set click listener for the post image to view post details
            card_image.setOnClickListener(v ->{
                Intent intent = new Intent(HomeActivity.this,PostActivity.class);
                intent.putExtra("post_id",post.getPostID());
                intent.putExtra("post_image",post.getImageUrl());
                intent.putExtra("post_name",post.getProductDisplayName());
                intent.putExtra("post_description",post.getDescription());
                intent.putExtra("post_price",post.getPrice());
                intent.putExtra("post_seller", post.getUserID());
                intent.putExtra("source", "home");
                startActivity(intent);
            });
        }
    }

    /**
     * Initialize the views.
     */
    private void init(){
        home = findViewById(R.id.btn_home);
        search = findViewById(R.id.btn_search);
        create = findViewById(R.id.btn_create);
        inbox = findViewById(R.id.btn_inbox);
        profile = findViewById(R.id.btn_profile);
    }
}