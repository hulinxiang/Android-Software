package com.example.myapplication.src.Remark;

public class AnonymousRemarkFactory implements RemarkFactory {
    @Override
    public RemarkDemo create(String text, String userEmail, String postId) {
        return new AnonymousRemark(text, userEmail, postId);
    }
}
