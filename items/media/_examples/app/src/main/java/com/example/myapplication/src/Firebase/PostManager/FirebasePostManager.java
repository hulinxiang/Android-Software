package com.example.myapplication.src.Firebase.PostManager;

import android.content.Context;

/**
 * @author Linxiang Hu
 * Manages a singleton instance of FirebasePostHelper for Firebase post management tasks.
 * This class ensures that there is only one instance of FirebasePostHelper across the application,
 * implementing a thread-safe Singleton pattern with lazy initialization.
 * @author Linxiang Hu u7633783
 */
public class FirebasePostManager {
    // Holds the singleton instance of FirebasePostHelper.
    private static FirebasePostHelper firebasePostHelper;

    /**
     * Provides a global point of access to the FirebasePostHelper instance.
     * If the instance does not already exist, it is created upon the first call to this method.
     * This method is synchronized to make it thread-safe, ensuring that only one instance is created
     * even if multiple threads access it simultaneously.
     * @param context The Android context. Currently not used in the instantiation but typically necessary for initializing context-related dependencies in Firebase.
     * @return The singleton instance of FirebasePostHelper.
     */
    public static synchronized FirebasePostHelper getInstance(Context context) {
        // Check if the instance has not been created yet.
        if (firebasePostHelper == null) {
            // Create a new instance of FirebasePostHelper.
            firebasePostHelper = new FirebasePostHelper();
        }
        // Return the existing instance.
        return firebasePostHelper;
    }
}