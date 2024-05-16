package com.example.myapplication.activity;

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

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.BPlusTree.Post.BPlusTreeManagerPost;
import com.example.myapplication.BPlusTree.Remark.BPlusTreeManagerRemark;
import com.example.myapplication.R;
import com.example.myapplication.activity.Image.GlideImageLoader;
import com.example.myapplication.src.Firebase.PostManager.FirebasePostManager;
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
 * The MyPostActivity class is an Android activity that displays the details of a user's post,
 * including its name, price, image, description, and comments. The main aim of this activity
 * is to provide a user interface for viewing and interacting with a specific post. It allows
 * the user to like the post, view and post comments, and delete their own comments.
 * The activity handles the initialization of views, retrieving post data from intents,
 * setting up click listeners for buttons, managing the display of comments, and handling
 * user interactions such as liking the post and posting comments.
 */
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
    private TextView write;
    private GridLayout gl_comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_post);
        init();
        showDetail();
        LikePostManager likePostManager = new LikePostManager(getApplicationContext());
        // id of the current post
        String post_id = getIntent().getStringExtra("post_id");
        // user id in this post
        String user_id = getIntent().getStringExtra("user_id");

        currentPost = BPlusTreeManagerPost.searchPostId(getApplicationContext(),post_id);
        currentUser = SessionManager.getInstance().getUser();
        // get like post list
        List<Post> likeList = currentUser.getLikePosts();

        showComment(currentPost);

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
                    likePostManager.likePost(currentPost.getPostID(),currentUser.getEmail());
                    //sync like
                    likePostManager.syncLikes(currentPost.getPostID());
                    // Show a toast message
                    Toast.makeText(MyPostActivity.this, "Like successful", Toast.LENGTH_SHORT).show();
                    // Log message for debugging
                    Log.d("LikeFeature", "Post liked");
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
                                    FirebasePostManager.getInstance(getApplicationContext()).deletePost(currentPost);
                                    BPlusTreeManagerPost.getTreeInstance(getApplicationContext()).remove(currentPost.getPostID());
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
                    Intent intent = new Intent(MyPostActivity.this,ProfileActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MyPostActivity.this, "Please write a comment", Toast.LENGTH_SHORT).show();
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

    private void showComment(Post currentPost){
        gl_comment.removeAllViews();
        //comment list
        List<RemarkDemo> list = BPlusTreeManagerRemark.get(currentPost.getPostID());

        for (RemarkDemo remark: list) {
            //get the layout from item_comment.xml
            View view = LayoutInflater.from(this).inflate(R.layout.item_comment, null);
            TextView comment_name = view.findViewById(R.id.comment_email);
            TextView comment_context = view.findViewById(R.id.comment_message);

            comment_name.setText(remark.getUserEmail());
            comment_context.setText(remark.getText());

            //get the height and weight from the screen
            int screenWidth = getResources().getDisplayMetrics().widthPixels;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(screenWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
            //add to grid layout
            gl_comment.addView(view, params);

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
                                    //update B plus tree
                                    BPlusTreeManagerRemark.delete(currentPost.getPostID(),remark);
                                    //update firebase
                                    FirebaseRemarkManager.getInstance(getApplicationContext()).deleteRemark(remark);
                                    Toast.makeText(v.getContext(), "Comment deleted", Toast.LENGTH_SHORT).show();
                                    // Refresh MyPostActivity
                                    Intent intent = new Intent(MyPostActivity.this,ProfileActivity.class);
                                    startActivity(intent);
                                    break;
                                case 1: // Cancel
                                    dialog.dismiss(); // Dismiss the dialog
                                    break;
                            }
                        });
                        builder.show(); // Show the AlertDialog
                });
                }
            }
    }

    private void postComment(String comment, boolean isAnonymous) {
        if(isAnonymous){
            //create new remark
            RemarkDemo newRemark = AnonymousRemarkFactoryManager.getInstance().create(comment,currentUser.getEmail(),currentPost.getPostID());
            //update B plus tree
            BPlusTreeManagerRemark.update(currentPost.getPostID(),newRemark);
            //update firebase
            FirebaseRemarkManager.getInstance(getApplicationContext()).addRemark(newRemark);
        }else{
            //firebase
            RemarkDemo newRemark = CommonRemarkFactoryManager.getInstance().create(comment,currentUser.getEmail(),currentPost.getPostID());
            //update B plus tree
            BPlusTreeManagerRemark.update(currentPost.getPostID(),newRemark);
            //update firebase
            FirebaseRemarkManager.getInstance(getApplicationContext()).addRemark(newRemark);
        }
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
        gl_comment = findViewById(R.id.gl_comment);
        write = findViewById(R.id.write);
    }
}