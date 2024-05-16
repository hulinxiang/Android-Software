package com.example.myapplication.src;

/**
 * Author: Wenhui Shi
 */
public class SessionManager {
    private static SessionManager instance;
    private User currentUser;

    // Private constructor to prevent instantiation from outside the class
    private SessionManager() { }

    // Public method to get the single instance of this class
    public static synchronized SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    // Method to set the current user when they log in
    public void setUser(User user) {
        this.currentUser = user;
    }

    // Method to get the currently logged-in user
    public User getUser() {
        return currentUser;
    }

    // Method to log out the current user
    public void clearUser() {
        this.currentUser = null;
    }
}
