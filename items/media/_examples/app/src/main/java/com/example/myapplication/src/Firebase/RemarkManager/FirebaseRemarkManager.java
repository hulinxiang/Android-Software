package com.example.myapplication.src.Firebase.RemarkManager;
import android.content.Context;

/**
 * @author Linxiang Hu u7633783
 * Manages a singleton instance of FirebaseRemarkHelper for Firebase remark management tasks.
 * This class ensures that there is only one instance of FirebaseRemarkHelper across the application,
 * implementing a thread-safe Singleton pattern with lazy initialization.
 */
public class FirebaseRemarkManager {
    // Holds the singleton instance of FirebaseRemarkHelper.
    private static FirebaseRemarkHelper firebaseRemarkHelper;

    /**
     * Provides a global point of access to the FirebaseRemarkHelper instance.
     * If the instance does not already exist, it is created upon the first call to this method.
     * This method is synchronized to make it thread-safe, ensuring that only one instance is created
     * even if multiple threads access it simultaneously.
     * @param context The Android context. Currently not used in the instantiation but typically necessary for initializing context-related dependencies in Firebase.
     * @return The singleton instance of FirebaseRemarkHelper.
     */
    public static synchronized FirebaseRemarkHelper getInstance(Context context) {
        // Check if the instance has not been created yet.
        if (firebaseRemarkHelper == null) {
            // Create a new instance of FirebaseRemarkHelper.
            firebaseRemarkHelper = new FirebaseRemarkHelper();
        }
        // Return the existing instance.
        return firebaseRemarkHelper;
    }
}