package com.example.myapplication.BPlusTree.Remark;


import com.example.myapplication.BPlusTree.BPlusTree;
import com.example.myapplication.src.Remark.RemarkDemo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Linxiang Hu u7633783
 * Manages the B+ tree for storing remarks associated with posts.
 */
public class BPlusTreeManagerRemark {

    // The singleton instance of the B+ tree storing remarks.
    private static BPlusTree<String, List<RemarkDemo>> remarkTree;

    /**
     * Retrieves the singleton instance of the B+ tree for remarks.
     *
     * @return The singleton instance of the B+ tree.
     */
    public static synchronized BPlusTree<String, List<RemarkDemo>> getTreeInstance() {
        if (remarkTree == null) {
            remarkTree = new BPlusTree<>();
        }
        return remarkTree;
    }

    /**
     * Retrieves remarks associated with a given post ID.
     *
     * @param postId The ID of the post to retrieve remarks for.
     * @return The list of remarks associated with the provided post ID, or an empty list if none found.
     */
    public static List<RemarkDemo> get(String postId) {
        List<List<RemarkDemo>> res = BPlusTreeManagerRemark.getTreeInstance().query(postId);
        if (res.isEmpty()) {
            return new ArrayList<>();
        }
        return res.get(0);
    }


    /**
     * Updates the remarks associated with a given post ID.
     *
     * @param postId     The ID of the post to update remarks for.
     * @param remarkDemo The remark to add/update for the specified post.
     */
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


    /**
     * Deletes a remark associated with a given post ID.
     *
     * @param postId     The ID of the post to delete the remark from.
     * @param remarkDemo The remark to delete from the specified post.
     * @return True if the deletion was successful, false otherwise.
     */
    public static boolean delete(String postId, RemarkDemo remarkDemo) {
        // Query the B+ tree for the list of remarks associated with the postId
        List<List<RemarkDemo>> res = BPlusTreeManagerRemark.getTreeInstance().query(postId);

        // Check if the result list is not empty and contains at least one list
        if (res != null && !res.isEmpty() && res.get(0) != null) {
            // Try to remove the specified remarkDemo from the first list
            // Return whether the removal was successful
            return res.get(0).remove(remarkDemo);
        }
        // If the list is empty or doesn't exist, return false
        return false;
    }

}
