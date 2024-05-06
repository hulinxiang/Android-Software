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
    private EditText imageUrlEditText;
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
        imageUrlEditText = findViewById(R.id.link);//这里因为我改了名字所以不知道是不是要改sorry
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
        String imageUrl = imageUrlEditText.getText().toString().trim();
        String commentText = "This is a sample comment\\nAnother sample comment"; // Example comments

        // Convert price to double
        double price;
        try {
            price = Double.parseDouble(priceText);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid price input", Toast.LENGTH_SHORT).show();
            return;
        }

        // Get the current user's ID (replace with your own logic)
        String userID = getCurrentUserID();

        // Create the post
        Post newPost = new Post(
                userID,
                "Women", // Example gender
                "Apparel", // Example masterCategory
                "Topwear", // Example subCategory
                "Tops", // Example articleType
                "Blue", // Example baseColour
                "Summer", // Example season
                2016, // Example year
                "Casual", // Example usage
                productDisplayName,
                price,
                status,
                imageUrl,
                description,
                commentText
        );

        // Show a message with the post details
        String postDetails = String.format("Post Created:\nProduct: %s\nPrice: %.2f\nStatus: %s\nDescription: %s\nImage URL: %s",
                newPost.getProductDisplayName(), newPost.getPrice(), newPost.getStatus(),
                newPost.getDescription(), newPost.getImageUrl());
        Toast.makeText(this, postDetails, Toast.LENGTH_LONG).show();
    }

    /**
     * Get the current user's ID.
     * Replace this method with your own logic to retrieve the current user's ID.
     */
    private String getCurrentUserID() {
        // Replace with your own logic to get the current user's ID
        return "comp6442";
    }

    /**
     * //1
     * String userID = getIntent().getStringExtra("userID");
     * //2
     *FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
     * if (currentUser != null) {
     *     String userID = currentUser.getUid();
     * }
     * //3
     * private String getCurrentUserID() {
     *     SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
     *     return sharedPreferences.getString("userID", "");
     * }
     */

}