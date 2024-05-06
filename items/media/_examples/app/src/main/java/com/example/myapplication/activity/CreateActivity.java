package com.example.myapplication.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.src.Post;
import com.example.myapplication.R;
import com.example.myapplication.BPlusTree.Post.BPlusTreeManagerPost;

public class CreateActivity extends AppCompatActivity {

    private EditText etProductDisplayName, etArticleType, etBaseColour, etMasterCategory, etSubCategory, etGender, etSeason, etYear, etUsage, etProductPrice, etProductStatus, etProductDescription, etFilename, etLink, etComments;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        // Initialize the Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialize UI components
        etProductDisplayName = findViewById(R.id.et_product_display_name);
        etArticleType = findViewById(R.id.et_article_type);
        etBaseColour = findViewById(R.id.et_base_colour);
        etMasterCategory = findViewById(R.id.et_master_category);
        etSubCategory = findViewById(R.id.et_sub_category);
        etGender = findViewById(R.id.et_gender);
        etSeason = findViewById(R.id.et_season);
        etYear = findViewById(R.id.et_year);
        etUsage = findViewById(R.id.et_usage);
        etProductPrice = findViewById(R.id.et_product_price);
        etProductStatus = findViewById(R.id.et_product_status);
        etProductDescription = findViewById(R.id.et_product_description);
        etFilename = findViewById(R.id.et_filename);
        etLink = findViewById(R.id.et_link);
        etComments = findViewById(R.id.et_comments);

        submitButton = findViewById(R.id.btn_submit_post);

        // Handle the submit button click
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPost();
            }
        });
    }

    // Function to create a new post
    private void createPost() {
        String productDisplayName = etProductDisplayName.getText().toString();
        String articleTypeName = etArticleType.getText().toString();
        String baseColour = etBaseColour.getText().toString();
        String masterCategoryName = etMasterCategory.getText().toString();
        String subCategoryName = etSubCategory.getText().toString();
        String gender = etGender.getText().toString();
        String season = etSeason.getText().toString();
        String yearString = etYear.getText().toString();
        String usage = etUsage.getText().toString();
        String productPriceString = etProductPrice.getText().toString();
        String productStatus = etProductStatus.getText().toString();
        String productDescription = etProductDescription.getText().toString();
        String filename = etFilename.getText().toString();
        String link = etLink.getText().toString();
        String commentText = etComments.getText().toString();

        // Validate required fields
        if (productDisplayName.isEmpty() || articleTypeName.isEmpty() || baseColour.isEmpty() || masterCategoryName.isEmpty() || productPriceString.isEmpty()) {
            Toast.makeText(this, "Please fill in the required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        double productPrice;
        int year;
        try {
            productPrice = Double.parseDouble(productPriceString);
            year = Integer.parseInt(yearString);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid input for price or year", Toast.LENGTH_SHORT).show();
            return;
        }

        String userID = ""; // Retrieve the userID from Firebase data or any other source

        Post newPost = new Post(userID, gender, masterCategoryName, subCategoryName, articleTypeName, baseColour, season, year, usage, productDisplayName, productPrice, productStatus, link, productDescription, commentText);

        // Retrieve the generated postID from the newPost object
        String postID = newPost.getPostID();

        // Save the post to B+ Tree
        BPlusTreeManagerPost.getTreeInstance(this).insert(postID, newPost);

        Toast.makeText(this, "Post created successfully!", Toast.LENGTH_SHORT).show();

        // Clear the input fields
        clearInputFields();
    }

    private void clearInputFields() {
        etProductDisplayName.setText("");
        etArticleType.setText("");
        etBaseColour.setText("");
        etMasterCategory.setText("");
        etSubCategory.setText("");
        etGender.setText("");
        etSeason.setText("");
        etYear.setText("");
        etUsage.setText("");
        etProductPrice.setText("");
        etProductStatus.setText("");
        etProductDescription.setText("");
        etFilename.setText("");
        etLink.setText("");
        etComments.setText("");
    }
}