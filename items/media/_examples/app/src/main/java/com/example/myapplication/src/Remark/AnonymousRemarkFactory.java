package com.example.myapplication.src.Remark;

/**
 * @author Linxiang Hu
 */
public class AnonymousRemarkFactory implements RemarkFactory {
    @Override
    public RemarkDemo create(String text, String userEmail, String postId) {
        return new AnonymousRemark(text, userEmail, postId);
    }

    @Override
    public RemarkDemo createWithIndex(String text, String userEmail, String postId, String index) {
        return new AnonymousRemark(text, userEmail, postId, index);
    }
}
