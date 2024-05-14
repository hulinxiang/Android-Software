package com.example.myapplication.BPlusTree.Remark;


import com.example.myapplication.BPlusTree.BPlusTree;
import com.example.myapplication.src.Remark.RemarkDemo;

import java.util.ArrayList;
import java.util.List;


public class BPlusTreeManagerRemark {
    private static BPlusTree<String, List<RemarkDemo>> remarkTree;

    public static synchronized BPlusTree<String, List<RemarkDemo>> getTreeInstance() {
        if (remarkTree == null) {
            remarkTree = new BPlusTree<>();
        }
        return remarkTree;
    }

    public static List<RemarkDemo> get(String postId) {
        List<List<RemarkDemo>> res = BPlusTreeManagerRemark.getTreeInstance().query(postId);
        if (res.isEmpty()) {
            return new ArrayList<>();
        }
        return res.get(0);
    }


    public static void update(String postId, RemarkDemo remarkDemo) {
        List<List<RemarkDemo>> res = BPlusTreeManagerRemark.getTreeInstance().query(postId);
        if (res.isEmpty()) {
            List<RemarkDemo> value = new ArrayList<>();
            value.add(remarkDemo);
            BPlusTreeManagerRemark.getTreeInstance().insert(postId, value);
        } else {
            res.get(0).add(remarkDemo);
        }
    }


    public static boolean delete(String postId, RemarkDemo remarkDemo) {
        // 从B+树中查询对应的postId
        List<List<RemarkDemo>> res = BPlusTreeManagerRemark.getTreeInstance().query(postId);

        // 检查获取的结果列表是否为空，以及是否至少包含一个列表元素
        if (res != null && !res.isEmpty() && res.get(0) != null) {
            // 尝试从第一个列表中移除指定的remarkDemo
            // 返回是否成功移除
            return res.get(0).remove(remarkDemo);
        }
        // 如果列表为空或不存在，返回false
        return false;
    }

}
