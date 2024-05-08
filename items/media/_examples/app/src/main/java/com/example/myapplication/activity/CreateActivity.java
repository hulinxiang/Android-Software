package com.example.myapplication.activity;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.myapplication.R;
import com.example.myapplication.BPlusTree.Post.BPlusTreeManagerPost;
import com.example.myapplication.src.Firebase.PostManager.FirebasePostManager;
import com.example.myapplication.src.Post;
import com.example.myapplication.src.SessionManager;
import com.example.myapplication.src.User;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Calendar;
import java.util.UUID;

public class CreateActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int STORAGE_PERMISSION_CODE = 2;
    private Uri imageUri;

    private EditText etProductDisplayName, etArticleType, etBaseColour, etMasterCategory, etSubCategory,
            etGender, etSeason, etYear, etUsage, etProductPrice, etProductStatus, etProductDescription, etComments;
    private Button submitButton, resetDefaultsButton;
    private ImageView returnButton, imagePreview, imageOverlay;
    private TextView tvSelectPhoto;
    private StorageReference storageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        // Request storage permissions
        requestStoragePermissions();

        // Initialize the Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialize Firebase Storage
        storageRef = FirebaseStorage.getInstance().getReference();

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
        etComments = findViewById(R.id.et_comments);

        submitButton = findViewById(R.id.btn_submit_post);
        resetDefaultsButton = findViewById(R.id.btn_reset_defaults);
        returnButton = findViewById(R.id.returnButton);
        imagePreview = findViewById(R.id.image_preview);
        imageOverlay = findViewById(R.id.image_overlay);
        tvSelectPhoto = findViewById(R.id.tv_select_photo);

        // Handle the image preview click
        imagePreview.setOnClickListener(v -> openFileChooser());

        // Handle the submit button click
        submitButton.setOnClickListener(view -> {
            if (imageUri != null) {
                uploadFile();
            } else {
                Toast.makeText(this, "Please select an image to upload", Toast.LENGTH_SHORT).show();
            }
        });

        // Handle the reset defaults button click
        resetDefaultsButton.setOnClickListener(view -> applyDefaultValues());

        // Handle the return button click
        returnButton.setOnClickListener(view -> {
            // Navigate back to the Home Activity
            Intent intent = new Intent(CreateActivity.this, HomeActivity.class);
            startActivity(intent);
            finish(); // Close the Create Activity
        });
    }

    private void requestStoragePermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Storage permission granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Storage permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Method to open file chooser for image selection
    private void openFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select a photo"), PICK_IMAGE_REQUEST);
    }

    // Handle result from image selection
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            imagePreview.setImageURI(imageUri);
            tvSelectPhoto.setVisibility(View.GONE);
            imageOverlay.setVisibility(View.VISIBLE);
        } else {
            Toast.makeText(this, "No image selected", Toast.LENGTH_SHORT).show();
        }
    }

    // Method to upload the file to Firebase Storage
    private void uploadFile() {
        if (imageUri != null) {
            String fileName = UUID.randomUUID().toString() + ".jpg";
            StorageReference fileReference = storageRef.child("posts/" + fileName);

            fileReference.putFile(imageUri)
                    .addOnSuccessListener(taskSnapshot -> fileReference.getDownloadUrl().addOnSuccessListener(uri -> {
                        String imageUrl = uri.toString();
                        createPost(imageUrl);
                    }))
                    .addOnFailureListener(e -> Toast.makeText(CreateActivity.this,
                            "Upload failed: " + e.getMessage(), Toast.LENGTH_SHORT).show());
        }
    }

    // Function to create a new post
    private void createPost(String imageUrl) {
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
        String commentText = etComments.getText().toString();

        // Validate required fields
        if (productDisplayName.isEmpty() || articleTypeName.isEmpty() || baseColour.isEmpty() ||
                masterCategoryName.isEmpty() || productPriceString.isEmpty()) {
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

        // Retrieve the current user's ID
        User currentUser = SessionManager.getInstance().getUser();
        String userID = currentUser != null ? currentUser.getUserId() : "";

        // Create a new post
        Post newPost = new Post(userID, gender, masterCategoryName, subCategoryName, articleTypeName,
                baseColour, season, year, usage, productDisplayName, productPrice, productStatus, imageUrl,
                productDescription, commentText);

        // Retrieve the generated postID from the newPost object
        String postID = newPost.getPostID();

        // Save the post to the BPlus Tree
        BPlusTreeManagerPost.getTreeInstance(this).insert(postID, newPost);

        // Save the post to Firebase
        FirebasePostManager.getInstance(this).addPost(newPost);

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
        etComments.setText("");
        imagePreview.setImageResource(R.drawable.ic_camera_alt_black_24dp);
        tvSelectPhoto.setVisibility(View.VISIBLE);
        imageOverlay.setVisibility(View.GONE);
        imageUri = null;
    }

    // Apply default values
    private void applyDefaultValues() {
        String defaultGender = "Unisex";

        // Apply predefined default values
        etGender.setText(defaultGender);
        etProductDisplayName.setText("Default Product Name");
        etArticleType.setText("T-shirt");
        etBaseColour.setText("Blue");
        etMasterCategory.setText("Clothing");
        etSubCategory.setText("Tops");
        etSeason.setText("Spring");
        etYear.setText(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
        etUsage.setText("Casual");
        etProductPrice.setText("29.99");
        etProductStatus.setText("Available");
        etProductDescription.setText("This is a default product description.");
        etComments.setText("Add comments here...");
    }
}
