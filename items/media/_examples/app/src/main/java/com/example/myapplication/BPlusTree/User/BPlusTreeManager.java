package com.example.myapplication.BPlusTree.User;

import android.content.Context;

import com.example.myapplication.entity.LoginNameBean;


public class BPlusTreeManager {
    private static BPlusTree<String, LoginNameBean> userTree;

    public static synchronized BPlusTree<String, LoginNameBean> getTreeInstance(Context context) {
        if (userTree == null) {
            // 假设TreeStorage是一个类，用于处理B+树的加载和保存
            userTree = TreeStorage.loadTree(context);
            if (userTree == null) {
                userTree = new BPlusTree<>();
            }
        }
        return userTree;
    }

    public static void saveTree(Context context) {
        TreeStorage.saveTree(userTree, context);
    }
}
