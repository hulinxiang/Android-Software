package com.example.myapplication.src.Firebase.PostManager;

import android.content.Context;

public class FirebasePostManager {
    //FirebaseApp.initializeApp(this); 这一行代码到时候要在项目启动的时候，就初始化执行
    private static FirebasePostHelper firebasePostHelper;

    public static synchronized FirebasePostHelper getInstance(Context context) {
        if (firebasePostHelper == null) {
            firebasePostHelper = new FirebasePostHelper();
        }
        return firebasePostHelper;
    }
}
