package com.example.myapplication.BPlusTree.User;

import android.content.Context;

import com.example.myapplication.BPlusTree.BPlusTree;

import com.example.myapplication.src.User;

import java.util.List;


/**
 * @author Linxiang Hu
 * Manages the B+ tree for storing user data.
 */
public class BPlusTreeManagerUser {

    // The singleton instance of the B+ tree storing user data.
    private static BPlusTree<String, User> userTree;

    /**
     * Retrieves the singleton instance of the B+ tree for user data.
     *
     * @param context The context for initializing the B+ tree.
     * @return The singleton instance of the B+ tree.
     */
    public static synchronized BPlusTree<String, User> getTreeInstance(Context context) {
        if (userTree == null) {
            userTree = new BPlusTree<>();
        }
        return userTree;
    }

    /**
     * Retrieves a user by their user ID from the B+ tree.
     *
     * @param context The context for accessing the B+ tree.
     * @param userId  The ID of the user to retrieve.
     * @return The user object corresponding to the provided user ID, or null if not found.
     */
    public static User getUserViaUserId(Context context, String userId) {
        // Retrieve all users from the B+ tree
        List<User> allUsers = BPlusTreeManagerUser.getTreeInstance(context).queryAllData();
        User ans = null;
        // Iterate through all users to find the one with the matching user ID
        for (User user : allUsers) {
            if (user.getUserId().equals(userId)) {
                ans = user;
                break;
            }
        }
        return ans;
    }


}
