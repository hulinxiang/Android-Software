package com.example.myapplication.BPlusTree.User_Post;

import android.content.Context;
import android.util.Log;

import com.example.myapplication.entity.LoginNameBean;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TreeStorage {

    private static final String FILENAME = "bplustree.dat";

    public static void saveTree(BPlusTree<String, LoginNameBean> tree, Context context) {
        try (FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(tree);
            Log.d("TreeStorage", "Tree successfully saved to internal storage.");
        } catch (Exception e) {
            Log.e("TreeStorage", "Error saving tree: " + e.getMessage(), e);
        }
    }

    public static BPlusTree<String, LoginNameBean> loadTree(Context context) {
        BPlusTree<String, LoginNameBean> tree = null;
        try (FileInputStream fis = context.openFileInput(FILENAME);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            tree = (BPlusTree<String, LoginNameBean>) ois.readObject();
            Log.d("TreeStorage", "Tree successfully loaded from internal storage.");
        } catch (Exception e) {
            Log.e("TreeStorage", "Error loading tree: " + e.getMessage(), e);
        }
        return tree;
    }
}
