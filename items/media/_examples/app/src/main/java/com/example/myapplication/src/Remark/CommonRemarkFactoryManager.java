package com.example.myapplication.src.Remark;

/**
 * @author Linxiang Hu u7633783
 * Manages a singleton instance of CommonRemarkFactory.
 * This class ensures that there is only one instance of CommonRemarkFactory across the application,
 * implementing a thread-safe Singleton pattern with lazy initialization.
 */
public class CommonRemarkFactoryManager {
    // Holds the singleton instance of CommonRemarkFactory.
    private static CommonRemarkFactory commonRemarkFactory;

    /**
     * Provides a global point of access to the CommonRemarkFactory instance.
     * If the instance does not already exist, it is created upon the first call to this method.
     * This method is synchronized to make it thread-safe, ensuring that only one instance is created
     * even if multiple threads access it simultaneously.
     * @return The singleton instance of CommonRemarkFactory.
     */
    public static synchronized CommonRemarkFactory getInstance() {
        // Check if the instance has not been created yet.
        if (commonRemarkFactory == null) {
            // Create and return a new instance of CommonRemarkFactory.
            commonRemarkFactory = new CommonRemarkFactory(); // Create a new instance of CommonRemarkFactory.
            return commonRemarkFactory;
        }
        return commonRemarkFactory;
    }
}