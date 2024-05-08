package com.example.myapplication.activity.Image;

import android.net.Uri;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.UUID;

public class ImageUploader {
    private StorageReference storageRef;

    public ImageUploader() {
        storageRef = FirebaseStorage.getInstance().getReference();
    }

    public void uploadImage(Uri imageUri, OnImageUploadListener listener) {
        if (imageUri != null) {
            String fileName = UUID.randomUUID().toString() + ".jpg";
            StorageReference fileReference = storageRef.child("posts/" + fileName);

            fileReference.putFile(imageUri)
                    .addOnSuccessListener(taskSnapshot -> fileReference.getDownloadUrl().addOnSuccessListener(uri -> {
                        String imageUrl = uri.toString();
                        listener.onImageUploadSuccess(imageUrl);
                    }))
                    .addOnFailureListener(e -> listener.onImageUploadFailure(e.getMessage()));
        }
    }

    public interface OnImageUploadListener {
        void onImageUploadSuccess(String imageUrl);
        void onImageUploadFailure(String errorMessage);
    }
}
