package com.example.myapplication.activity.Image;

import android.net.Uri;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.UUID;

/**
 * Author: Yingxuan Tang
 */
public class ImageUploader {
    private StorageReference storageRef;

    public ImageUploader() {
        storageRef = FirebaseStorage.getInstance().getReference();
    }

    // Uploads an image to Firebase Storage
    public void uploadImage(Uri imageUri, OnImageUploadListener listener) {
        if (imageUri != null) {
            String fileName = UUID.randomUUID().toString() + ".jpg";    // Generate a unique file name
            StorageReference fileReference = storageRef.child("posts/" + fileName);    // Create a file reference

            // Upload the image to Firebase Storage
            fileReference.putFile(imageUri)
                    .addOnSuccessListener(taskSnapshot -> fileReference.getDownloadUrl().addOnSuccessListener(uri -> {
                        // Success callback
                        String imageUrl = uri.toString();
                        // Notify upload success
                        listener.onImageUploadSuccess(imageUrl);
                    }))
                    // Failure callback
                    .addOnFailureListener(e -> listener.onImageUploadFailure(e.getMessage()));
        }
    }

    // Callback interface for image upload
    public interface OnImageUploadListener {
        void onImageUploadSuccess(String imageUrl);   // Callback method for upload success
        void onImageUploadFailure(String errorMessage);   // Callback method for upload failure
    }
}
