package com.example.myapplication.src.Firebase.PostManager;

import android.content.Context;

/**
 * Author: Linxiang Hu
 */
public class FirebasePostManager {
    private static FirebasePostHelper firebasePostHelper;

    public static synchronized FirebasePostHelper getInstance(Context context) {
        if (firebasePostHelper == null) {
            firebasePostHelper = new FirebasePostHelper();
        }
        return firebasePostHelper;
    }
}