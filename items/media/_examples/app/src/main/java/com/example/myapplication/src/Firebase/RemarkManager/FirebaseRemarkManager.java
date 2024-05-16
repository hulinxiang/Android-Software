package com.example.myapplication.src.Firebase.RemarkManager;
import android.content.Context;

public class FirebaseRemarkManager {
    private static FirebaseRemarkHelper firebaseRemarkHelper;

    public static synchronized FirebaseRemarkHelper getInstance(Context context) {
        if (firebaseRemarkHelper == null) {
            firebaseRemarkHelper = new FirebaseRemarkHelper();
        }
        return firebaseRemarkHelper;
    }
}