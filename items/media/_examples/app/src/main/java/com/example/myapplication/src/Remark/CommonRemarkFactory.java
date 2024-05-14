package com.example.myapplication.src.Remark;

public class CommonRemarkFactory implements RemarkFactory {

    @Override
    public RemarkDemo create(String text, String userEmail, String postId) {
        return new CommonRemark(text, userEmail, postId);
    }

    @Override
    public RemarkDemo createWithIndex(String text, String userEmail, String postId, String index) {
        return new CommonRemark(text, userEmail, postId, index);
    }
}
