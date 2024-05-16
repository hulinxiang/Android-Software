package com.example.myapplication.src.Remark;

/**
 * @author Linxiang Hu u7633783
 * Manages a singleton instance of AnonymousRemarkFactory.
 * This class ensures that there is only one instance of AnonymousRemarkFactory across the application,
 * implementing a thread-safe Singleton pattern with lazy initialization.
 */
public class AnonymousRemarkFactoryManager {
    // Holds the singleton instance of AnonymousRemarkFactory.
    private static AnonymousRemarkFactory anonymousRemarkFactory;

    /**
     * Provides a global point of access to the AnonymousRemarkFactory instance.
     * If the instance does not already exist, it is created upon the first call to this method.
     * This method is synchronized to make it thread-safe, ensuring that only one instance is created
     * even if multiple threads access it simultaneously.
     *
     * @return The singleton instance of AnonymousRemarkFactory.
     */
    public static synchronized AnonymousRemarkFactory getInstance() {
        if (anonymousRemarkFactory == null) {
            // Check if the instance has not been created yet.
            // Create a new instance of AnonymousRemarkFactory.
            anonymousRemarkFactory = new AnonymousRemarkFactory();
            // Return the newly created instance.
            return anonymousRemarkFactory;
        }
        //Return the existing instance.
        return anonymousRemarkFactory;
    }
}