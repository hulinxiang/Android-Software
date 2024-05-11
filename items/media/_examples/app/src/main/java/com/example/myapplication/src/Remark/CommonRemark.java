package com.example.myapplication.src.Remark;

public class CommonRemark extends RemarkDemo {

    CommonRemark(String text, String userEmail, String postId) {
        this.text = text;
        this.userEmail = userEmail;
        this.postId = postId;
    }


    @Override
    void updateText(String newText) {
        this.text = newText;
    }
}
