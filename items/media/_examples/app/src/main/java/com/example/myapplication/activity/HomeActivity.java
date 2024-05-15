package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
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

    private Handler handler = new Handler();
    private Runnable runnable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
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
                Intent intent = new Intent(HomeActivity.this, CreateActivity.class);
                startActivity(intent);
            }
        });


        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        //网格视图
        gl_post = findViewById(R.id.gl_home);
        showPost();

    }

    private void showPost() {
        Log.d("Thread========", "showPost started");

        gl_post.removeAllViews();
        //需要一个list
        List<Post> list = BPlusTreeManagerPost.randomRecommender(getApplicationContext());

        for (Post post : list) {
            //get the layout from item_card.xml
            View view = LayoutInflater.from(this).inflate(R.layout.item_card, null);
            ImageView card_image = view.findViewById(R.id.card_image);
            TextView card_name = view.findViewById(R.id.card_name);
            TextView card_price = view.findViewById(R.id.card_price);

            GlideImageLoader.loadImage(HomeActivity.this, post.getImageUrl(), card_image);
            card_name.setText(post.getProductDisplayName());
            card_price.setText(String.valueOf(post.getPrice()));

            //get the height and weight from the screen
            int screenWidth = getResources().getDisplayMetrics().widthPixels;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(screenWidth / 2, ViewGroup.LayoutParams.WRAP_CONTENT);
            //add to grid layout
            gl_post.addView(view, params);

            //click image jump to post detail page
            card_image.setOnClickListener(v -> {
                Intent intent = new Intent(HomeActivity.this, PostActivity.class);
                intent.putExtra("post_id", post.getPostID());
                intent.putExtra("post_image", post.getImageUrl());
                intent.putExtra("post_name", post.getProductDisplayName());
                intent.putExtra("post_description", post.getDescription());
                intent.putExtra("post_price", post.getPrice());
                intent.putExtra("post_seller", post.getUserID());
                intent.putExtra("source", "home");
                startActivity(intent);
            });
        }
    }


    private void init() {
        home = findViewById(R.id.btn_home);
        search = findViewById(R.id.btn_search);
        create = findViewById(R.id.btn_create);
        profile = findViewById(R.id.btn_profile);
    }

    private void startRepeatingTask() {
        runnable = new Runnable() {
            @Override
            public void run() {
                runOnUiThread(() -> {
                    Log.d("Thread====", "Running on UI thread");
                    showPost();
                });
                handler.postDelayed(this, 10000);
            }
        };
        handler.post(runnable);
    }

    private void stopRepeatingTask() {
        handler.removeCallbacks(runnable);
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopRepeatingTask(); // 停止重复的任务
        Log.d("Thread===", "我消灭了线程");
    }

    @Override
    protected void onStart() {
        super.onStart();
        startRepeatingTask(); // 在Activity开始时重新启动任务
        Log.d("Thread===", "我启动了线程");
    }

}