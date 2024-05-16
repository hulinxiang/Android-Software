package com.example.myapplication.BPlusTree.User;

import android.content.Context;

import com.example.myapplication.BPlusTree.BPlusTree;

import com.example.myapplication.src.User;

import java.util.List;

/**
 * Author: Linxiang Hu
 */
public class BPlusTreeManagerUser {
    private static BPlusTree<String, User> userTree;

    public static synchronized BPlusTree<String, User> getTreeInstance(Context context) {
        if (userTree == null) {
            userTree = new BPlusTree<>();
        }
        return userTree;
    }

    public static User getUserViaUserId(Context context, String userId) {
        List<User> allUsers = BPlusTreeManagerUser.getTreeInstance(context).queryAllData();
        User ans = null;
        for (User user : allUsers) {
            if (user.getUserId().equals(userId)) {
                ans = user;
                break;
            }
        }
        return ans;
    }
}