package com.example.myapplication.activity.loginUsingBPlusTree;

import android.content.Context;
import android.util.Log;

import com.example.myapplication.BPlusTree.BPlusTree;
import com.example.myapplication.BPlusTree.User.BPlusTreeManagerUser;
import com.example.myapplication.src.SearchManager;
import com.example.myapplication.src.User;

import java.util.List;

/**
 * @author Yingxuan Tang
 *
 * The LoginCheckService class provides methods to validate user registration and login credentials.
 * It uses a BPlus Tree data structure to manage user data and validate login and registration details.
 */
public class LoginCheckService {
    private Context context;

    /**
     * Constructor for LoginCheckService.
     *
     * @param context  The context in which the LoginCheckService is used.
     */
    public LoginCheckService(Context context) {
        this.context = context;
    }

    // Check if the email and password are valid
    public boolean checkValid(String email, String password) {
        // Get the BPlusTree instance for users
        BPlusTree<String, User> tree = BPlusTreeManagerUser.getTreeInstance(context);
        // Query the tree to check if the email already exists
        List<User> users = tree.query(email);
        if (!users.isEmpty()) {
            return false;   // User already exists
        }
        // Check if the password is not empty and if the email and password format are valid
        return !password.isEmpty() && SearchManager.validateUsername(email) && SearchManager.validatePassword(password);
    }

    /**
     * Checks if the login credentials are valid.
     *
     * @param name  The email or username to be checked.
     * @param pwd  The password to be checked.
     * @return  True if the login credentials are valid; false otherwise.
     *
     * Method:
     * - Retrieves the BPlus Tree instance for users.
     * - Queries the tree to find the user by email.
     * - Iterates through the found users and checks if the email and password match.
     * - Logs the process and returns true if a match is found; false otherwise.
     */
    public boolean loginCheck(String name, String pwd) {
        // Get the BPlusTree instance for users
        BPlusTree<String, User> tree = BPlusTreeManagerUser.getTreeInstance(context);
        // Query the tree to find the user by email
        List<User> allUsers = tree.query(name);
        if (allUsers.size() != 0) {
            for (User user : allUsers) {
                // Iterate through all found users
                if (user != null && user.getEmail() != null && user.getPasswordHash() != null) {
                    Log.d("LoginCheck", "Checking user in BPlusTree: " + user.getEmail());
                    // Check if the email and password match
                    if (user.getEmail().equals(name) && user.getPasswordHash().equals(User.hashPassword(pwd))) {
                        Log.d("LoginCheck", "Login successful for user: " + name);
                        return true;   // Login successful
                    } else {
                        Log.d("LoginFailed", "False Password: " + User.hashPassword(pwd) + "  Right Password: " + user.getPasswordHash());
                    }
                }
            }
        } else {
            Log.d("Empty User List", "The User List is empty");
        }
        Log.d("LoginCheck", "Login failed for user: " + name + "  password: " + pwd);

        return false;   // Login failed
    }
}