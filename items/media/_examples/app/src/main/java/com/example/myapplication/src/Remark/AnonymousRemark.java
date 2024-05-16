package com.example.myapplication.src.Remark;

/**
 * @author Linxiang Hu u7633783
 * Represents an anonymous remark associated with posts, hiding the user's email and including details like post ID.
 * This class extends the abstract RemarkDemo, adding functionality to anonymize the user and handle index management.
 */
public class AnonymousRemark extends RemarkDemo {

    /**
     * Constructor for AnonymousRemark without an explicit index. It auto-generates an index based on a static counter.
     * @param text The text content of the remark.
     * @param userEmail Ignored in this context as the remark is anonymous.
     * @param postId The identifier of the post associated with the remark.
     */
    AnonymousRemark(String text, String userEmail, String postId) {
        this.text = text;
        this.userEmail = "Anonymous User";
        this.postId = postId;
        this.index = String.valueOf(RemarkDemo.count++);
    }

    /**
     * Constructor for AnonymousRemark with a specified index. Uses the provided index directly.
     * @param text The text content of the remark.
     * @param userEmail Ignored in this context as the remark is anonymous.
     * @param postId The identifier of the post associated with the remark.
     * @param index The index position of the remark.
     */
    AnonymousRemark(String text, String userEmail, String postId, String index) {
        this.text = text;
        this.userEmail = "Anonymous User";
        this.postId = postId;
        this.index = index;
        RemarkDemo.count++;
    }
}