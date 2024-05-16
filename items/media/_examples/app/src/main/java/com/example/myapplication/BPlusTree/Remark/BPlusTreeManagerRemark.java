package com.example.myapplication.BPlusTree.Remark;
import com.example.myapplication.BPlusTree.BPlusTree;
import com.example.myapplication.src.Remark.RemarkDemo;
import java.util.ArrayList;
import java.util.List;


/**
 * Author: Linxiang Hu
 */
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
        // Query the corresponding postId from the B+ tree
        List<List<RemarkDemo>> res = BPlusTreeManagerRemark.getTreeInstance().query(postId);

        // Check that the obtained list of results is empty and contains at least one list element
        if (res != null && !res.isEmpty() && res.get(0) != null) {
            // Attempt to remove the specified remarkDemo from the first list
            // Returns whether the removal was successful
            return res.get(0).remove(remarkDemo);
        }
        // If the list is empty or does not exist, return false
        return false;
    }

}
