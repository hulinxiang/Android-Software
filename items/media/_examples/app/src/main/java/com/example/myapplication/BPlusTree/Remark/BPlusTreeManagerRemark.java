package com.example.myapplication.BPlusTree.Remark;

import android.content.Context;

import com.example.myapplication.BPlusTree.BPlusTree;
import com.example.myapplication.src.Remark.RemarkDemo;

import java.util.List;


public class BPlusTreeManagerRemark {
    private static BPlusTree<String, RemarkDemo> remarkTree;

    public static synchronized BPlusTree<String, RemarkDemo> getTreeInstance(Context context) {
        if (remarkTree == null) {
            remarkTree = new BPlusTree<>();
        }
        return remarkTree;
    }

}
