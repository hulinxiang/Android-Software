package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.BPlusTree.Post.BPlusTreeManagerPost;
import com.example.myapplication.R;
import com.example.myapplication.activity.Image.GlideImageLoader;
import com.example.myapplication.src.Post;
import com.example.myapplication.src.SessionManager;
import com.example.myapplication.src.User;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.callback.Callback;

public class ProfileActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private LinearLayout home;
    private LinearLayout search;
    private LinearLayout create;
    private LinearLayout inbox;
    private LinearLayout profile;
    private CardView cardView1;

    private TextView textName;
    private TextView textEmail;
    private Button editProfileButton;

    private LinearLayout postsContainer, likesContainer, buyContainer;
    private TextView postsButton, likesButton, buyButton;

    private GridLayout postsGrid, likesGrid, buyGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        init();

        // Initialize views
        toolbar = findViewById(R.id.toolbar);

        // Set up toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Profile");

        // User profile display
        User currentUser = SessionManager.getInstance().getUser();
        if (currentUser != null) {
            textName.setText(currentUser.getName());
            textEmail.setText(currentUser.getEmail());
        }

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
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
                Intent intent = new Intent(ProfileActivity.this, CreateActivity.class);
                startActivity(intent);
            }
        });

        inbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, InboxActivity.class);
                startActivity(intent);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        // Add click listener for edit profile button
        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
                startActivity(intent);
            }
        });

        // Set click listeners
        postsButton.setOnClickListener(v -> updateViews("posts"));
        likesButton.setOnClickListener(v -> updateViews("likes"));
        buyButton.setOnClickListener(v -> updateViews("buy"));

        // Initial display setup for posts
        updateViews("posts");

        // Initially update button texts
        updateButtonCounts();
    }

    @SuppressLint("SetTextI18n")
    private void updateButtonCounts() {
        int postsCount = 24; // Fetch actual post count
        int likesCount = 120; // Fetch actual likes count
        int buyCount = 15; // Fetch actual buy count

        postsButton.setText(postsCount + " Posts");
        likesButton.setText(likesCount + " Likes");
        buyButton.setText(buyCount + " Buy It");
    }

    private void updateViews(String view) {
        // Hide all grids initially
        postsContainer.setVisibility(view.equals("posts") ? View.VISIBLE : View.GONE);
        likesContainer.setVisibility(view.equals("likes") ? View.VISIBLE : View.GONE);
        buyContainer.setVisibility(view.equals("buy") ? View.VISIBLE : View.GONE);


        // Show only the selected grid and update it
        if (view.equals("posts")) {
            postsContainer.setVisibility(View.VISIBLE);
            showPost(postsGrid,MyPostActivity.class);
            //showPostCanDelete();
        } else if (view.equals("likes")) {
            likesContainer.setVisibility(View.VISIBLE);
            showPost(likesGrid, PostActivity.class);
        } else if (view.equals("buy")) {
            buyContainer.setVisibility(View.VISIBLE);
            showPost(buyGrid, BuyPostActivity.class);
        }

        // Update button styles to indicate which is active
        updateButtonStyles(view);
    }



    private void updateButtonStyles(String view) {
        postsButton.setTextColor(getResources().getColor(view.equals("posts") ? R.color.colorAccent : android.R.color.white));
        likesButton.setTextColor(getResources().getColor(view.equals("likes") ? R.color.colorAccent : android.R.color.white));
        buyButton.setTextColor(getResources().getColor(view.equals("buy") ? R.color.colorAccent : android.R.color.white));
    }

//    private void showPostCanDelete() {
//        // Assume data is ready or handle cases where it might not be
//        postsGrid.removeAllViews();
//        //get list from ownList
//        List<Post> list = BPlusTreeManagerPost.randomRecommender(getApplicationContext());
//
//        for (Post post: list){
//            //get the layout from item_card.xml
//            View view = LayoutInflater.from(this).inflate(R.layout.item_card,null);
//            ImageView card_image = view.findViewById(R.id.card_image);
//            TextView card_name = view.findViewById(R.id.card_name);
//            TextView card_price = view.findViewById(R.id.card_price);
//
//            GlideImageLoader.loadImage(ProfileActivity.this,post.getImageUrl(),card_image);
//            card_name.setText(post.getProductDisplayName());
//            card_price.setText(String.valueOf(post.getPrice()));
//
//            //get the height and weight from the screen
//            int screenWidth = getResources().getDisplayMetrics().widthPixels;
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(screenWidth/2, ViewGroup.LayoutParams.WRAP_CONTENT);
//            //add to grid layout
//            postsGrid.addView(view,params);
//
//            //click image jump to post detail page
//            card_image.setOnClickListener(v ->{
//                Intent intent = new Intent(ProfileActivity.this,PostActivity.class);
//                intent.putExtra("post_id",post.getPostID());
//                intent.putExtra("post_image",post.getImageUrl());
//                intent.putExtra("post_name",post.getProductDisplayName());
//                intent.putExtra("post_description",post.getDescription());
//                intent.putExtra("post_price",post.getPrice());
//                intent.putExtra("post_seller", post.getUserID());
//                startActivity(intent);
//            });
//        }
//
//    }
    private void showPost(GridLayout grid, Class<?> activityClass){
        // Assume data is ready or handle cases where it might not be
        grid.removeAllViews();
        // Dynamically add views based on the type of grid

        List<Post> list = new ArrayList<>();
        if (grid == postsGrid) {
            //get post from likesList
            list = BPlusTreeManagerPost.randomRecommender(getApplicationContext());

        }
         else if (grid == likesGrid) {
             //get post from likesList
            list = BPlusTreeManagerPost.randomRecommender(getApplicationContext());

        } else if (grid == buyGrid) {
             //get post from buyList
            list = BPlusTreeManagerPost.randomRecommender(getApplicationContext());
        }

        for (Post post: list){
            //get the layout from item_card.xml
            View view = LayoutInflater.from(this).inflate(R.layout.item_card,null);
            ImageView card_image = view.findViewById(R.id.card_image);
            TextView card_name = view.findViewById(R.id.card_name);
            TextView card_price = view.findViewById(R.id.card_price);

            GlideImageLoader.loadImage(ProfileActivity.this,post.getImageUrl(),card_image);
            card_name.setText(post.getProductDisplayName());
            card_price.setText(String.valueOf(post.getPrice()));

            //get the height and weight from the screen
            int screenWidth = getResources().getDisplayMetrics().widthPixels;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(screenWidth/2, ViewGroup.LayoutParams.WRAP_CONTENT);
            //add to grid layout

            grid.addView(view,params);

            //click image jump to post detail page
            card_image.setOnClickListener(v ->{
                Intent intent = new Intent(ProfileActivity.this, activityClass);
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

    private void init() {
        home = findViewById(R.id.btn_home);
        search = findViewById(R.id.btn_search);
        create = findViewById(R.id.btn_create);
        inbox = findViewById(R.id.btn_inbox);
        profile = findViewById(R.id.btn_profile);
        textEmail = findViewById(R.id.text_email);
        textName = findViewById(R.id.text_name);
        editProfileButton = findViewById(R.id.btn_edit_profile); // Correct initialization
        // Initialize Views
        postsContainer = findViewById(R.id.posts_container);
        likesContainer = findViewById(R.id.likes_container);
        buyContainer = findViewById(R.id.buy_container);
        postsButton = findViewById(R.id.posts_button);
        likesButton = findViewById(R.id.likes_button);
        buyButton = findViewById(R.id.buy_button);

        //  Grid layout
        postsGrid = findViewById(R.id.gl_myPosts);
        likesGrid = findViewById(R.id.gl_likePosts);
        buyGrid = findViewById(R.id.gl_buyPosts);
    }
}
