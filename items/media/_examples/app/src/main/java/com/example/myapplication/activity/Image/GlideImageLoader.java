package com.example.myapplication.activity.Image;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class GlideImageLoader {
    private static GlideImageLoader instance;
    private Context context;

    private GlideImageLoader(Context context) {
        this.context = context.getApplicationContext();
    }

    public static GlideImageLoader getInstance(Context context) {
        if (instance == null) {
            instance = new GlideImageLoader(context);
        }
        return instance;
    }

    public void loadImage(String imageUrl, ImageView imageView) {
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