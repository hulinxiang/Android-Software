package com.example.myapplication.activity.loginUsingBPlusTree;

import android.content.Context;
import android.util.Log;

import com.example.myapplication.BPlusTree.BPlusTree;
import com.example.myapplication.BPlusTree.User.BPlusTreeManagerUser;
import com.example.myapplication.src.SearchManager;
import com.example.myapplication.src.User;

import java.util.List;

public class LoginCheckService {
    private Context context;

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

    // Check if the login credentials are valid
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