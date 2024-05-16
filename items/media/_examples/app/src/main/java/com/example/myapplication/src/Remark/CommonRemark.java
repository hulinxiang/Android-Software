package com.example.myapplication.src.Remark;

/**
 * @author Linxiang Hu u7633783
 * Represents a specific type of remark associated with posts, including additional details like user email and post ID.
 * This class extends the abstract RemarkDemo, adding functionality to handle index management and user association.
 */
public class CommonRemark extends RemarkDemo {

    /**
     * Constructor for CommonRemark without an explicit index. It auto-generates an index based on a static counter.
     * @param text The text content of the remark.
     * @param userEmail The email address of the user making the remark.
     * @param postId The identifier of the post associated with the remark.
     */
    CommonRemark(String text, String userEmail, String postId) {
        this.text = text;
        this.userEmail = userEmail;
        this.postId = postId;
        this.index = String.valueOf(RemarkDemo.count++);
    }

    /**
     * Constructor for CommonRemark with a specified index. Uses the provided index directly.
     * @param text The text content of the remark.
     * @param userEmail The email address of the user making the remark.
     * @param postId The identifier of the post associated with the remark.
     * @param index The index order position of the remark.
     */
    CommonRemark(String text, String userEmail, String postId, String index) {
        this.text = text;
        this.userEmail = userEmail;
        this.postId = postId;
        this.index = index;
        RemarkDemo.count++;
    }

}