package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.BPlusTree.Post.BPlusTreeManagerPost;
import com.example.myapplication.R;
import com.example.myapplication.activity.Image.GlideImageLoader;
import com.example.myapplication.activity.loginUsingBPlusTree.LoginActivityBPlusTree;
import com.example.myapplication.activity.loginUsingBPlusTree.RegisterActivityBPlusTree;
import com.example.myapplication.src.Post;
import com.example.myapplication.src.SessionManager;
import com.example.myapplication.src.User;

import java.util.List;

public class PostActivity extends AppCompatActivity {
    private TextView post_name;
    private TextView post_price;
    private ImageView post_image;

    private ImageView post_return;
    private ImageView post_like;

    private TextView seller_name;

    private ImageView post_star;
    private TextView post_description;

    private Button post_buy;


    // Member variable to track the like state

    private boolean isLiked;

    private Post currentPost;
    private User currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        init();
        showDetail();
        //id of the current post
        String post_id = getIntent().getStringExtra("post_id");
        //user id in this post
        String user_id = getIntent().getStringExtra("user_id");
        //the class for the current post
        currentPost = BPlusTreeManagerPost.searchPostId(getApplicationContext(),post_id);
        currentUser = SessionManager.getInstance().getUser();
        List<Post> likeList = currentUser.getLikePosts();

        // Initialize the like button
        if (currentPost != null && currentUser != null) {
            isLiked = checkLike(currentPost,likeList);
            initializeLikeButton(isLiked);
        } else {
            Toast.makeText(this, "Error loading post or user data.", Toast.LENGTH_LONG).show();
        }


        post_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            // Check the current state and update the image and show a toast accordingly
                if (!isLiked) {
                    // Change the icon to red (liked)
                    ((ImageView) v).setImageResource(R.drawable.ic_favorite_red_24dp);
                    //code add post to like list
                    currentUser.updateLikes(currentPost);
                    // Show a toast message
                    Toast.makeText(PostActivity.this, "Like successful", Toast.LENGTH_SHORT).show();
                    // Log message for debugging
                    Log.d("LikeFeature", "Post liked");
                } else {
                    // Change the icon back to white (unliked)
                    ((ImageView) v).setImageResource(R.drawable.ic_favorite_white_24dp);
                    //code remove post from like list
                    currentUser.removeLikes(currentPost);
                    // Show a toast message
                    Toast.makeText(PostActivity.this, "Like cancelled", Toast.LENGTH_SHORT).show();
                    // Log message for debugging
                    Log.d("LikeFeature", "Like cancelled");
                }

                // Check the source and navigate back to the profile if needed
                String source = getIntent().getStringExtra("source");
                if ("profile".equals(source)) {
                    // Navigate back to the profile interface and refresh it
                    Intent intent = new Intent(PostActivity.this, ProfileActivity.class);
                    // Optionally add flags to clear the activity stack
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    // Finish the current activity
                    finish();
                }

            }
        });
    }

    private void initializeLikeButton(boolean isLiked) {
        post_like.setImageResource(isLiked ? R.drawable.ic_favorite_red_24dp : R.drawable.ic_favorite_white_24dp);
    }

    private boolean checkLike(Post post, List<Post> likeList) {
        isLiked = false;
        for (Post likedPost : likeList) {
            if (likedPost.getPostID().equals(post.getPostID())) {
                isLiked = true;
                break;
            }
        }
        return isLiked;
    }


    private void showDetail(){
        String p_name = getIntent().getStringExtra("post_name");
        String p_image = getIntent().getStringExtra("post_image");
        Double p_price = getIntent().getDoubleExtra("post_price",0);
        String p_description = getIntent().getStringExtra("post_description");
        String p_seller = getIntent().getStringExtra("post_seller");

        GlideImageLoader.loadImage(PostActivity.this,p_image,post_image);
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

    }






    private void init(){
        post_name = findViewById(R.id.post_name);
        post_buy = findViewById(R.id.btn_post_buy);
        post_description = findViewById(R.id.post_description);
        post_image = findViewById(R.id.post_image);
        post_like = findViewById(R.id.btn_post_like);
        post_return = findViewById(R.id.btn_post_return);
        post_star = findViewById(R.id.post_star);
        seller_name = findViewById(R.id.seller_name);
        post_price = findViewById(R.id.post_price);
    }
}