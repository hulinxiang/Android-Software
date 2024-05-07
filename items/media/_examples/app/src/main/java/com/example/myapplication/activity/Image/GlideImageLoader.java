package com.example.myapplication.activity.Image;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class GlideImageLoader {
    public static void loadImage(Context context, String imageUrl, ImageView imageView) {
        StorageReference storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(imageUrl);
        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(context)
                        .load(uri)
                        .into(imageView);
            }
        });
    }
}

// Usage例子:
//    String imageUrl = "gs://login-register-firebase-94766.appspot.com/6001.jpg";
//    ImageView imageView = findViewById(R.id.imageViewTest);
//    GlideImageLoader.loadImage(this, imageUrl, imageView);