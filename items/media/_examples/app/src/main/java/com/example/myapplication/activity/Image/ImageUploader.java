package com.example.myapplication.activity.Image;

import android.net.Uri;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.UUID;

/**
 * @author Yingxuan Tang
 *
 * The ImageUploader class handles the uploading of images to Firebase Storage.
 * It provides a method to upload an image and obtain its download URL upon successful upload.
 */
public class ImageUploader {
    private StorageReference storageRef;

    /**
     * Constructor for ImageUploader.
     * Initializes the Firebase Storage reference.
     */
    public ImageUploader() {
        storageRef = FirebaseStorage.getInstance().getReference();
    }



    /**
     * Uploads an image to Firebase Storage.
     *
     * @param imageUri  The URI of the image to be uploaded.
     * @param listener  The callback listener to handle upload success or failure.
     *
     * Method:
     * - Generates a unique file name for the image.
     * - Creates a StorageReference for the file in Firebase Storage.
     * - Uploads the image to Firebase Storage.
     * - On successful upload, retrieves the download URL and notifies the listener.
     * - On failure, notifies the listener with an error message.
     */
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

    /**
     * Callback interface for image upload.
     * Provides methods to handle success and failure scenarios of the image upload process.
     */
    public interface OnImageUploadListener {
        /**
         * Called when the image upload is successful.
         *
         * @param imageUrl  The URL of the uploaded image.
         */
        void onImageUploadSuccess(String imageUrl);   // Callback method for upload success

        /**
         * Called when the image upload fails.
         *
         * @param errorMessage  The error message describing the failure.
         */
        void onImageUploadFailure(String errorMessage);   // Callback method for upload failure
    }
}
