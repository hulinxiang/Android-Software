package com.example.myapplication.BPlusTree.Remark;

import android.content.Context;

import com.example.myapplication.BPlusTree.BPlusTree;
import com.example.myapplication.src.Remark.RemarkDemo;

import java.util.List;


public class BPlusTreeManagerRemark {
    private static BPlusTree<String, List<RemarkDemo>> remarkTree;

    public static synchronized BPlusTree<String, List<RemarkDemo>> getTreeInstance(Context context) {
        if (remarkTree == null) {
            remarkTree = new BPlusTree<>();
        }
        return remarkTree;
    }

}
