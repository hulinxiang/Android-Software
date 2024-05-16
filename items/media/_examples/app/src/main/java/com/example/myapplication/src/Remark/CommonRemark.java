package com.example.myapplication.src.Remark;

/**
 * @author Linxiang Hu
 */
public class CommonRemark extends RemarkDemo {

    CommonRemark(String text, String userEmail, String postId) {
        this.text = text;
        this.userEmail = userEmail;
        this.postId = postId;
        this.index = String.valueOf(RemarkDemo.count++);
    }

    CommonRemark(String text, String userEmail, String postId, String index) {
        this.text = text;
        this.userEmail = userEmail;
        this.postId = postId;
        this.index = index;
        RemarkDemo.count++;
    }

}