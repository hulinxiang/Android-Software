package com.example.myapplication.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.BPlusTree.Post.BPlusTreeManagerPost;
import com.example.myapplication.R;
import com.example.myapplication.activity.Image.GlideImageLoader;
import com.example.myapplication.src.Firebase.PostManager.FirebasePostHelper;
import com.example.myapplication.src.Firebase.PostManager.FirebasePostManager;
import com.example.myapplication.src.Post;
import com.example.myapplication.src.SessionManager;
import com.example.myapplication.src.User;

import java.util.List;

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

    private Post currentPost;

    private User currentUser;

    private boolean isLiked;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_post);
        init();
        showDetail();

        //id of the current post
        String post_id = getIntent().getStringExtra("post_id");
        //user id in this post
        String user_id = getIntent().getStringExtra("user_id");

        currentPost = BPlusTreeManagerPost.searchPostId(getApplicationContext(),post_id);
        currentUser = SessionManager.getInstance().getUser();
        //get like post list
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
                    Toast.makeText(MyPostActivity.this, "Like successful", Toast.LENGTH_SHORT).show();
                    // Log message for debugging
                    Log.d("LikeFeature", "Post liked");

                } else {
                    // Change the icon back to white (unliked)
                    ((ImageView) v).setImageResource(R.drawable.ic_favorite_white_24dp);
                    //code remove post from like list
                    currentUser.removeLikes(currentPost);
                    // Show a toast message
                    Toast.makeText(MyPostActivity.this, "Like cancelled", Toast.LENGTH_SHORT).show();
                    // Log message for debugging
                    Log.d("LikeFeature", "Like cancelled");
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

        GlideImageLoader.loadImage(MyPostActivity.this,p_image,post_image);
        post_description.setText(p_description);
        post_price.setText(String.valueOf(p_price));
        post_name.setText(p_name);
        seller_name.setText(p_seller);

        //id of the current post
        String post_id = getIntent().getStringExtra("post_id");
        //user id in this post
        String user_id = getIntent().getStringExtra("user_id");
        //the class for the current post
        currentPost = BPlusTreeManagerPost.searchPostId(getApplicationContext(),post_id);


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
                // Log message when the delete button is clicked
                Log.d("DeletePost", "Delete button clicked");

                // Create an AlertDialog to confirm the deletion
                new AlertDialog.Builder(MyPostActivity.this)
                        .setTitle("Confirm Deletion")  // Title of the dialog
                        .setMessage("Are you sure you want to delete this post?")  // Message to show
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Log message when deletion is confirmed
                                Log.d("DeletePost", "Deletion confirmed");

                                // Code to delete the post can be placed here
                                if (currentPost != null) {
                                    FirebasePostHelper firebaseHelper = new FirebasePostHelper();
                                    firebaseHelper.deletePost(currentPost);
                                    Toast.makeText(MyPostActivity.this, "Deleting post...", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(MyPostActivity.this, "Post not found", Toast.LENGTH_LONG).show();
                                }

                                // Intent to go back to the ProfileActivity after deletion
                                Intent intent = new Intent(MyPostActivity.this, ProfileActivity.class);
                                startActivity(intent);
                                finish();  // Close the current activity
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Log message when deletion is cancelled
                                Log.d("DeletePost", "Deletion cancelled");

                                // Dismiss the dialog and do nothing
                                dialog.dismiss();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();  // Display the dialog
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