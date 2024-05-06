package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.src.Post;
import com.example.myapplication.src.PostList;
import com.example.myapplication.src.User;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private ImageView slideMenu;
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

        slideMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,SlideActivity.class);
                startActivity(intent);
            }
        });

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

        //网格视图
        gl_post = findViewById(R.id.gl_home);
        showPost();

    }

    private void showPost(){
        //需要一个list
        List<Post> list = new ArrayList<>();
        // Adding sample data to the list
        list.add(new Post("1","@drawable/favorite_img_1", "Nature", 1,"1","description"));
        list.add(new Post("2","@drawable/favorite_img_2", "Cityscape", 2,"1","description"));
        list.add(new Post("3","@drawable/favorite_img_3", "Ocean", 3,"1","description"));
        list.add(new Post("4","@drawable/favorite_img_4", "Mountains", 4,"1","description"));
        list.add(new Post("5","@drawable/favorite_img_1", "Nature", 5,"1","description"));
        list.add(new Post("6","@drawable/favorite_img_2", "Cityscape", 6,"1","description"));
        list.add(new Post("7","@drawable/favorite_img_3", "Ocean", 7,"1","description"));
        list.add(new Post("8","@drawable/favorite_img_4", "Mountains", 8,"1","description"));

        for (Post post: list){
            //get the layout from item_card.xml
            View view = LayoutInflater.from(this).inflate(R.layout.item_card,null);
            ImageView card_image = view.findViewById(R.id.card_image);
            TextView card_name = view.findViewById(R.id.card_name);
            TextView card_price = view.findViewById(R.id.card_price);

            //set value to each part
            //card_image.setImageURI(Uri.parse(post.getImageUrl()));
            card_image.setImageResource(R.drawable.favorite_img_1);
            card_name.setText(post.getProductDisplayName());
            card_price.setText(String.valueOf(post.getPrice()));

            //get the height and weight from the screen
            int screenWidth = getResources().getDisplayMetrics().widthPixels;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(screenWidth/2, ViewGroup.LayoutParams.WRAP_CONTENT);
            //add to grid layout
            gl_post.addView(view,params);

            //click image jump to post detail page
            card_image.setOnClickListener(v ->{
                Intent intent = new Intent(HomeActivity.this,PostActivity.class);
                intent.putExtra("post_id",post.getPostID());
                intent.putExtra("post_image",post.getImageUrl());
                intent.putExtra("post_name",post.getProductDisplayName());
                intent.putExtra("post_description",post.getDescription());
                intent.putExtra("post_price",post.getPrice());
                intent.putExtra("post_seller", post.getUserID());
                startActivity(intent);
            });
        }
    }


    private void init(){
        slideMenu = findViewById(R.id.btn_slide_menu);
        home = findViewById(R.id.btn_home);
        search = findViewById(R.id.btn_search);
        create = findViewById(R.id.btn_create);
        inbox = findViewById(R.id.btn_inbox);
        profile = findViewById(R.id.btn_profile);
    }

}