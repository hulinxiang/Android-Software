package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplication.BPlusTree.Post.BPlusTreeManagerPost;
import com.example.myapplication.R;
import com.example.myapplication.activity.Image.GlideImageLoader;
import com.example.myapplication.src.Post;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private static final String TAG = "SearchActivity";

    private LinearLayout home;
    private LinearLayout search;
    private LinearLayout create;
    private LinearLayout inbox;
    private LinearLayout profile;
    private ImageView searchButton;
    private EditText search_input;
    private GridLayout gl_post;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        init();
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchActivity.this,CreateActivity.class);
                startActivity(intent);
            }
        });

        inbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchActivity.this,InboxActivity.class);
                startActivity(intent);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchActivity.this,ProfileActivity.class);
                startActivity(intent);
            }
        });


        // Adding a listener to handle changes in the EditText
        search_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty()) {
                    gl_post.removeAllViews();  // Clear the grid if text is cleared
                    Toast.makeText(SearchActivity.this, "Please enter a search keyword", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Listener for the search button
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword = search_input.getText().toString();
                if (!keyword.isEmpty()) {
                    showPost(keyword);
                } else {
                    gl_post.removeAllViews();  // Clear the grid if no keyword is entered
                    Toast.makeText(SearchActivity.this, "Please enter a search keyword", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void showPost(String keyword){
        gl_post.removeAllViews();  // Clear all views in the GridLayout

        if (keyword.isEmpty()) {
            Toast.makeText(this, "Please enter a search keyword", Toast.LENGTH_SHORT).show();
            return;
        }

        Log.d(TAG, "showPost method called with keyword: " + keyword);
        Toast.makeText(SearchActivity.this, "Search for: " + keyword, Toast.LENGTH_SHORT).show();

        //search list
        //List<Post> list = BPlusTreeManagerPost.searchKeyword(getApplicationContext(),keyword);
        List<Post> fullList = BPlusTreeManagerPost.searchKeyword(getApplicationContext(), keyword);
        List<Post> list = fullList.size() > 8 ? fullList.subList(0, 8) : fullList; // Ensure we only take up to 8 items

        if (list.isEmpty()) {
            Toast.makeText(this, "No results found for: " + keyword, Toast.LENGTH_SHORT).show();
            return;
        }
        for (Post post: list){
            //get the layout from item_card.xml
            View view = LayoutInflater.from(this).inflate(R.layout.item_card,null);
            ImageView card_image = view.findViewById(R.id.card_image);
            TextView card_name = view.findViewById(R.id.card_name);
            TextView card_price = view.findViewById(R.id.card_price);

            //set value to each part
            String post_name  = post.getProductDisplayName();
            Double post_price  = post.getPrice();
            String post_description  = post.getDescription();
            String post_image  = post.getImageUrl();
            String post_id =  post.getPostID();
            String user_id =  post.getUserID();

            GlideImageLoader.loadImage(SearchActivity.this,post.getImageUrl(),card_image);
            //card_image.setImageURI(Uri.parse(post_image));
            card_name.setText(post_name);
            card_price.setText(String.valueOf(post_price));
            //get the height and weight from the screen
            int screenWidth = getResources().getDisplayMetrics().widthPixels;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(screenWidth/2, ViewGroup.LayoutParams.WRAP_CONTENT);
            //add to grid layout
            gl_post.addView(view,params);

            //click image jump to post detail page
            card_image.setOnClickListener(v ->{
                Intent intent = new Intent(SearchActivity.this,PostActivity.class);
                intent.putExtra("post_id",post_id);
                intent.putExtra("post_image",post_image);
                intent.putExtra("post_name",post_name);
                intent.putExtra("post_description",post_description);
                intent.putExtra("post_price",post_price);
                intent.putExtra("post_seller", user_id);
                startActivity(intent);
            });
        }
    }

    private void init(){
        home = findViewById(R.id.btn_home);
        search = findViewById(R.id.btn_search);
        create = findViewById(R.id.btn_create);
        inbox = findViewById(R.id.btn_inbox);
        profile = findViewById(R.id.btn_profile);
        search_input =  findViewById(R.id.search_input);
        searchButton = findViewById(R.id.search_button);
        //grid layout
        gl_post = findViewById(R.id.gl_search);
    }

}