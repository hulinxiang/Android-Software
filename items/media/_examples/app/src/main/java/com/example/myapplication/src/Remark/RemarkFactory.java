package com.example.myapplication.src.Remark;

/**
 * @author Linxiang Hu
 * An interface defining a factory for creating remarks or comments associated with a post.
 * It provides methods to create remarks with basic details or with an additional index.
 */
public interface RemarkFactory {
    /**
     * Creates a RemarkDemo object with the provided text, user email, and post ID.
     *
     * @param text The text content of the remark.
     * @param userEmail The email address of the user making the remark.
     * @param postId The identifier of the post to which the remark is related.
     * @return A new instance of RemarkDemo representing the remark.
     */
    RemarkDemo create(String text, String userEmail, String postId);


    /**
     * Creates a RemarkDemo object with the provided text, user email, post ID, and an index.
     *
     * @param text The text content of the remark.
     * @param userEmail The email address of the user making the remark.
     * @param postId The identifier of the post to which the remark is related.
     * @param index The index of the remark stored in firebase.
     * @return A new instance of RemarkDemo representing the remark with an index.
     */
    RemarkDemo createWithIndex(String text, String userEmail, String postId, String index);
}
