package com.example.myapplication.src.Remark;

public class CommonRemarkFactory implements RemarkFactory {

    @Override
    public RemarkDemo create(String text, String userEmail, String postId) {
        return new CommonRemark(text, userEmail, postId);
    }
}
