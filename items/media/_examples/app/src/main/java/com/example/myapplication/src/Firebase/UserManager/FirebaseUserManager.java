package com.example.myapplication.src.Firebase.UserManager;

import android.content.Context;

/**
 * @author Linxiang Hu u7633783
 * Manages a singleton instance of FirebaseUserHelper for Firebase user management tasks.
 * This class ensures that there is only one instance of FirebaseUserHelper across the application,
 * implementing a thread-safe Singleton pattern with lazy initialization.
 */
public class FirebaseUserManager {
    // Holds the singleton instance of FirebaseUserHelper.
    private static FirebaseUserHelper firebaseUserHelper;

    /**
     * Provides a global point of access to the FirebaseUserHelper instance.
     * If the instance does not already exist, it is created upon the first call to this method.
     * This method is synchronized to make it thread-safe, ensuring that only one instance is created
     * even if multiple threads access it simultaneously.
     * @param context The Android context. Currently not used in the instantiation but typically necessary for initializing Firebase context-related dependencies.
     * @return The singleton instance of FirebaseUserHelper.
     */
    public static synchronized FirebaseUserHelper getInstance(Context context) {
        // Check if the instance has not been created yet.
        if (firebaseUserHelper == null) {
            // Create a new instance of FirebaseUserHelper.
            firebaseUserHelper = new FirebaseUserHelper();
        }
        // Return the existing instance.
        return firebaseUserHelper;
    }
}