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

    public boolean checkValid(String email, String password) {
        BPlusTree<String, User> tree = BPlusTreeManagerUser.getTreeInstance(context);
        List<User> users = tree.query(email);
        if (!users.isEmpty()) {
            return false; // 用户已存在
        }
        return !password.isEmpty() && SearchManager.validateUsername(email) && SearchManager.validatePassword(password);
    }

    public boolean loginCheck(String name, String pwd) {
        BPlusTree<String, User> tree = BPlusTreeManagerUser.getTreeInstance(context);
        List<User> allUsers = tree.query(name);
        if (allUsers.size() != 0) {
            for (User user : allUsers) {
                if (user != null && user.getEmail() != null && user.getPasswordHash() != null) {
                    Log.d("LoginCheck", "Checking user in BPlusTree: " + user.getEmail());
                    if (user.getEmail().equals(name) && user.getPasswordHash().equals(User.hashPassword(pwd))) {
                        Log.d("LoginCheck", "Login successful for user: " + name);
                        return true;
                    } else {
                        Log.d("LoginFailed", "False Password: " + User.hashPassword(pwd) + "  Right Password: " + user.getPasswordHash());
                    }
                }
            }
        } else {
            Log.d("Empty User List", "The User List is empty");
        }
        Log.d("LoginCheck", "Login failed for user: " + name + "  password: " + pwd);

        return false;
    }
}