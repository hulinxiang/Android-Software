package com.example.myapplication.src.Remark;

/**
 * @author Linxiang Hu
 * The AnonymousRemarkFactory class implements the RemarkFactory interface.
 * It provides methods to create instances of the AnonymousRemark class.
 */
public class AnonymousRemarkFactory implements RemarkFactory {

    /**
     * Creates a new AnonymousRemark with the specified text, user email, and post ID.
     *
     * @param text the text of the remark
     * @param userEmail the email of the user who made the remark
     * @param postId the ID of the post the remark is associated with
     * @return a new AnonymousRemark with the specified values
     */
    @Override
    public RemarkDemo create(String text, String userEmail, String postId) {
        return new AnonymousRemark(text, userEmail, postId);
    }

    /**
     * Creates a new AnonymousRemark with the specified text, user email, post ID, and index.
     *
     * @param text the text of the remark
     * @param userEmail the email of the user who made the remark
     * @param postId the ID of the post the remark is associated with
     * @param index the index of the remark
     * @return a new AnonymousRemark with the specified values
     */
    @Override
    public RemarkDemo createWithIndex(String text, String userEmail, String postId, String index) {
        return new AnonymousRemark(text, userEmail, postId, index);
    }
}