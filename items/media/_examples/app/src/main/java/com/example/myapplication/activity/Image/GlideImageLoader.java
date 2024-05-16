package com.example.myapplication.activity.Image;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * The GlideImageLoader class is used to load images into an ImageView using Glide and Firebase Storage.
 * It provides a static method to load an image from a URL into an ImageView.
 *
 * @author Yichi Zhang u7748799
 */
public class GlideImageLoader {
    private Context context;

    /**
     * Constructor for the GlideImageLoader class.
     * Initializes the context of the GlideImageLoader.
     *
     * @param context the context in which the GlideImageLoader is being used
     */
    public GlideImageLoader(Context context) {
        this.context = context;
    }

    /**
     * Loads an image from a URL into an ImageView.
     * This method retrieves the download URL from Firebase Storage and uses Glide to load the image.
     * If the retrieval is successful, the image is loaded into the ImageView.
     *
     * @param context the context in which the image is being loaded
     * @param imageUrl the URL of the image to load
     * @param imageView the ImageView into which the image is loaded
     */
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