package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.BPlusTree.Post.BPlusTreeManagerPost;
import com.example.myapplication.BPlusTree.Remark.BPlusTreeManagerRemark;
import com.example.myapplication.R;
import com.example.myapplication.activity.Image.GlideImageLoader;
import com.example.myapplication.src.BuyPostManager;
import com.example.myapplication.src.Firebase.RemarkManager.FirebaseRemarkManager;
import com.example.myapplication.src.LikePostManager;
import com.example.myapplication.src.Post;
import com.example.myapplication.src.Remark.AnonymousRemarkFactoryManager;
import com.example.myapplication.src.Remark.CommonRemarkFactoryManager;
import com.example.myapplication.src.Remark.RemarkDemo;
import com.example.myapplication.src.SessionManager;
import com.example.myapplication.src.User;

import java.util.List;

/**
 * @author Wenhui Shi u7773637
 * PostActivity is an Android activity class that displays details of a post and allows users to interact with the post by liking it, purchasing it,
 * and writing comments. It retrieves the post ID and user ID from the intent extras and initializes the necessary views and data.
 * The main aim of this class is to provide a user interface for viewing and interacting with individual posts in the application.
 * It handles functionalities such as liking a post, checking if the post is liked, purchasing a post, posting comments, and displaying post details and comments.
 */
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
    private TextView write;
    private GridLayout gl_comment;
    // Member variable to track the like state
    private boolean isLiked;
    private boolean isPurchased;
    private Post currentPost;
    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        init();
        showDetail();
        // id of the current post
        String post_id = getIntent().getStringExtra("post_id");
        // user id in this post
        String user_id = getIntent().getStringExtra("user_id");
        // the class for the current post
        currentPost = BPlusTreeManagerPost.searchPostId(getApplicationContext(), post_id);
        currentUser = SessionManager.getInstance().getUser();
        List<Post> likeList = currentUser.getLikePosts();
        showComment(currentPost);
        LikePostManager likePostManager = new LikePostManager(getApplicationContext());

        // Initialize the like button
        if (currentPost != null && currentUser != null) {
            isLiked = checkLike(currentPost, likeList);
            initializeLikeButton(isLiked);
        } else {
            Toast.makeText(this, "Error loading post or user data.", Toast.LENGTH_LONG).show();
        }

        post_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post_like.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Check the current state and update the image and show a toast accordingly
                        if (!isLiked) {
                            // Change the icon to red (liked)
                            ((ImageView) v).setImageResource(R.drawable.ic_favorite_red_24dp);
                            //code add post to like list
                            currentUser.updateLikes(currentPost);
                            likePostManager.likePost(currentPost.getPostID(),currentUser.getEmail());
                            //sync like
                            likePostManager.syncLikes(currentPost.getPostID());
                            // Show a toast message
                            Toast.makeText(PostActivity.this, "Like successful", Toast.LENGTH_SHORT).show();
                            // Log message for debugging
                            Log.d("LikeFeature", "Post liked");

                        }
                    }
                });
            }
        });

        isPurchased = checkIfPurchased(currentPost);

        post_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPurchased) {
                    // Show a message indicating the product is already purchased
                    Toast.makeText(PostActivity.this, "You have already purchased this product", Toast.LENGTH_SHORT).show();
                } else {
                    // Show a confirmation dialog
                    showPurchaseConfirmationDialog();
                }
            }
        });

        //write comment
        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCommentDialog();
            }
        });
    }

    private void showCommentDialog() {
        // Inflate the dialog layout
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.dialog_comment, null);

        // Initialize dialog views
        EditText editTextComment = dialogView.findViewById(R.id.edit_text_comment);
        CheckBox checkBoxAnonymous = dialogView.findViewById(R.id.checkbox_anonymous);
        Button buttonPost = dialogView.findViewById(R.id.button_post);
        Button buttonCancel = dialogView.findViewById(R.id.button_cancel);

        // Build and show the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        final AlertDialog dialog = builder.create();
        dialog.show();

        // Set the post button click listener
        buttonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = editTextComment.getText().toString().trim();
                boolean isAnonymous = checkBoxAnonymous.isChecked();

                if (!comment.isEmpty()) {
                    postComment(comment, isAnonymous);
                    dialog.dismiss();
                    Intent intent = new Intent(PostActivity.this,ProfileActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(PostActivity.this, "Please write a comment", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Set the cancel button click listener
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void postComment(String comment, boolean isAnonymous) {
       if(isAnonymous){
           // create new remark
           RemarkDemo newRemark = AnonymousRemarkFactoryManager.getInstance().create(comment,currentUser.getEmail(),currentPost.getPostID());
           // update B plus tree
           BPlusTreeManagerRemark.update(currentPost.getPostID(),newRemark);
           // update firebase
           FirebaseRemarkManager.getInstance(getApplicationContext()).addRemark(newRemark);
       }else{
           // firebase
           RemarkDemo newRemark = CommonRemarkFactoryManager.getInstance().create(comment,currentUser.getEmail(),currentPost.getPostID());
           // update B plus tree
           BPlusTreeManagerRemark.update(currentPost.getPostID(),newRemark);
           // update firebase
           FirebaseRemarkManager.getInstance(getApplicationContext()).addRemark(newRemark);
       }
    }

    // Method to check if the product is in the buy list
    private boolean checkIfPurchased(Post post) {
        // Replace this with your actual logic to check if the post is in the buy list
        return currentUser.getBuyPosts().contains(post);
    }

    // Method to show the purchase confirmation dialog
    private void showPurchaseConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Purchase Confirmation")
                .setMessage("Once you choose to purchase, you cannot cancel it. Do you want to proceed?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        BuyPostManager BuyPostManager = new BuyPostManager(getApplicationContext());
                        // Handle purchase logic here
                        handlePurchase();
                        // code add post to like list
                        currentUser.updateBuys(currentPost);
                        BuyPostManager.buyPost(currentPost.getPostID(),currentUser.getEmail());
                        // sync like
                        BuyPostManager.syncBuys(currentPost.getPostID());
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    // Method to handle the purchase logic
    private void handlePurchase() {
        // Add the post to the buy list
        currentUser.getBuyPosts().add(currentPost);
        // Show a success message
        Toast.makeText(PostActivity.this, "Purchase successful", Toast.LENGTH_SHORT).show();
        // Log message for debugging
        Log.d("PurchaseFeature", "Post purchased");
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

    private void showDetail () {
        String p_name = getIntent().getStringExtra("post_name");
        String p_image = getIntent().getStringExtra("post_image");
        Double p_price = getIntent().getDoubleExtra("post_price", 0);
        String p_description = getIntent().getStringExtra("post_description");
        String p_seller = getIntent().getStringExtra("post_seller");

        GlideImageLoader.loadImage(PostActivity.this, p_image, post_image);
        post_description.setText(p_description);
        post_price.setText(String.valueOf(p_price));
        post_name.setText(p_name);
        seller_name.setText(p_seller);

        // back button
        post_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void showComment(Post currentPost){
        // comment list
        List<RemarkDemo> list = BPlusTreeManagerRemark.get(currentPost.getPostID());

        for (RemarkDemo remark: list){
            // get the layout from item_comment.xml
            View view = LayoutInflater.from(this).inflate(R.layout.item_comment,null);
            TextView comment_name = view.findViewById(R.id.comment_email);
            TextView comment_context = view.findViewById(R.id.comment_message);

            comment_name.setText(remark.getUserEmail());
            comment_context.setText(remark.getText());

            // get the height and weight from the screen
            int screenWidth = getResources().getDisplayMetrics().widthPixels;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(screenWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
            // add to grid layout
            gl_comment.addView(view,params);

            if(remark.getUserEmail().equals(currentUser.getEmail())) {
                comment_context.setOnClickListener(v -> {
                    // Create an AlertDialog to show the options
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setTitle("Choose an action");
                    String[] options = {"Delete Comment", "Cancel"};
                    builder.setItems(options, (dialog, which) -> {
                        switch (which) {
                            case 0: // Delete Comment
                                // Logic to delete the comment
                                // update B plus tree
                                BPlusTreeManagerRemark.delete(currentPost.getPostID(),remark);
                                // update firebase
                                FirebaseRemarkManager.getInstance(getApplicationContext()).deleteRemark(remark);
                                Toast.makeText(v.getContext(), "Comment deleted", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(PostActivity.this,ProfileActivity.class);
                                startActivity(intent);
                                break;
                            case 1:  // Cancel
                                dialog.dismiss();  // Dismiss the dialog
                                break;
                        }
                    });
                    builder.show();  // Show the AlertDialog
                });
            }
        }
    }

    private void init () {
        post_name = findViewById(R.id.post_name);
        post_buy = findViewById(R.id.btn_post_buy);
        post_description = findViewById(R.id.post_description);
        post_image = findViewById(R.id.post_image);
        post_like = findViewById(R.id.btn_post_like);
        post_return = findViewById(R.id.btn_post_return);
        post_star = findViewById(R.id.post_star);
        seller_name = findViewById(R.id.seller_name);
        post_price = findViewById(R.id.post_price);
        write = findViewById(R.id.write);
        gl_comment = findViewById(R.id.gl_comment);
    }
}