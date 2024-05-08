package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.activity.Image.GlideImageLoader;

public class MyPostActivity extends AppCompatActivity {
    private TextView post_name;
    private TextView post_price;
    private ImageView post_image;

    private ImageView post_return;
    private ImageView post_like;

    private TextView seller_name;

    private ImageView post_star;
    private TextView post_description;

    private  ImageView post_delete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_post);
        init();
        showDetail();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //showDetail();
    }

    private void showDetail(){
        String p_name = getIntent().getStringExtra("post_name");
        String p_image = getIntent().getStringExtra("post_image");
        Double p_price = getIntent().getDoubleExtra("post_price",0);
        String p_description = getIntent().getStringExtra("post_description");
        String p_seller = getIntent().getStringExtra("post_seller");

        GlideImageLoader.loadImage(MyPostActivity.this,p_image,post_image);
        post_description.setText(p_description);
        post_price.setText(String.valueOf(p_price));
        post_name.setText(p_name);
        seller_name.setText(p_seller);

        //back button
        post_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //delete button
        post_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyPostActivity.this,ProfileActivity.class);
                startActivity(intent);
            }
        });

    }


    private void init(){
        post_name = findViewById(R.id.post_name);
        post_delete = findViewById(R.id.btn_post_delete);
        post_description = findViewById(R.id.post_description);
        post_image = findViewById(R.id.post_image);
        post_like = findViewById(R.id.btn_post_like);
        post_return = findViewById(R.id.btn_post_return);
        post_star = findViewById(R.id.post_star);
        seller_name = findViewById(R.id.seller_name);
        post_price = findViewById(R.id.post_price);
    }
}