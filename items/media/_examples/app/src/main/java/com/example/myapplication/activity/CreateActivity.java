package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.myapplication.R;
import com.example.myapplication.src.Post;

public class CreateActivity extends AppCompatActivity {
    private EditText productDisplayNameEditText;
    private EditText priceEditText;
    private EditText statusEditText;
    private EditText descriptionEditText;
    private EditText filenameEditText;
    private EditText linkEditText;
    private Button createPostButton;
    private ImageView returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        // Initialize views
        init();

        // Set return button click listener
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Go back to the previous activity
            }
        });

        // Set create post button click listener
        createPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPost();
            }
        });
    }

    /**
     * Initialize all views.
     */
    private void init() {
        productDisplayNameEditText = findViewById(R.id.productDisplayName);
        priceEditText = findViewById(R.id.price);
        statusEditText = findViewById(R.id.status);
        descriptionEditText = findViewById(R.id.description);
        filenameEditText = findViewById(R.id.filename);
        linkEditText = findViewById(R.id.link);
        createPostButton = findViewById(R.id.createPost);
        returnButton = findViewById(R.id.returnButton);
    }

    /**
     * Create a new post based on the input fields and show a message with the post details.
     */
    private void createPost() {
        String productDisplayName = productDisplayNameEditText.getText().toString().trim();
        String priceText = priceEditText.getText().toString().trim();
        String status = statusEditText.getText().toString().trim();
        String description = descriptionEditText.getText().toString().trim();
        String filename = filenameEditText.getText().toString().trim();
        String link = linkEditText.getText().toString().trim();
        String commentText = "This is a sample comment || Another sample comment"; // Example comments

        // Convert price to double
        double price;
        try {
            price = Double.parseDouble(priceText);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid price input", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create the post
        Post newPost = new Post(
                "1", // Example post ID
                null, // No Tag
                productDisplayName,
                price,
                status,
                description,
                filename,
                link,
                commentText // Add here, maybe be removed later
        );

        // Show a message with the post details
        String postDetails = String.format("Post Created:\nProduct: %s\nPrice: %.2f\nStatus: %s\nDescription: %s\nFilename: %s\nLink: %s",
                newPost.getProductDisplayName(), newPost.getPrice(), newPost.getStatus(), newPost.getDescription(), newPost.getFilename(), newPost.getLink());

        Toast.makeText(this, postDetails, Toast.LENGTH_LONG).show();
    }
}