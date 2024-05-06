package com.example.myapplication.activity.Test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.myapplication.R;

import android.net.Uri;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class GlideActivityTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_test);

        String imageUrl = "gs://login-register-firebase-94766.appspot.com/6001.jpg";
        ImageView imageView = findViewById(R.id.imageViewTest);

        StorageReference storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(imageUrl);

        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(GlideActivityTest.this)
                        .load(uri)
                        .into(imageView);
            }
        });

    }
}