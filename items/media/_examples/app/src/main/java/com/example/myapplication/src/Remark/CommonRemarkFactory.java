package com.example.myapplication.src.Remark;

/**
 * @author Linxiang Hu
 * Factory class for creating common remarks. Implements the RemarkFactory interface.
 * This factory is specific for generating CommonRemark objects, which can include both indexed and non-indexed remarks.
 */
public class CommonRemarkFactory implements RemarkFactory {

    /**
     * Creates a CommonRemark object without an index.
     * @param text The text content of the remark.
     * @param userEmail The email address of the user making the remark.
     * @param postId The identifier of the post to which the remark is related.
     * @return A new CommonRemark object containing the provided information.
     */
    @Override
    public RemarkDemo create(String text, String userEmail, String postId) {
        return new CommonRemark(text, userEmail, postId);
    }

    /**
     * Creates a CommonRemark object with an index.
     * This method is particularly useful when remarks need to be ordered or categorized within a larger set.
     * @param text The text content of the remark.
     * @param userEmail The email address of the user making the remark.
     * @param postId The identifier of the post to which the remark is related.
     * @param index The index the remark.
     * @return A new CommonRemark object that includes an index along with the standard information.
     */
    @Override
    public RemarkDemo createWithIndex(String text, String userEmail, String postId, String index) {
        return new CommonRemark(text, userEmail, postId, index);
    }
}