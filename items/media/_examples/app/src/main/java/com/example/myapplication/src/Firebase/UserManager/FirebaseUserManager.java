package com.example.myapplication.src.Firebase.UserManager;

import android.content.Context;

import com.example.myapplication.src.Firebase.PostManager.FirebasePostHelper;

public class FirebaseUserManager {
    private static FirebaseUserHelper firebaseUserHelper;

    public static synchronized FirebaseUserHelper getInstance(Context context) {
        if (firebaseUserHelper == null) {
            firebaseUserHelper = new FirebaseUserHelper();
        }
        return firebaseUserHelper;
    }

}
