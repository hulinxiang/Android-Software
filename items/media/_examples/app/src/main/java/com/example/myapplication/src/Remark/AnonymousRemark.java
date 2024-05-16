package com.example.myapplication.src.Remark;

/**
 * Author: Linxiang Hu
 */
public class AnonymousRemark extends RemarkDemo {

    AnonymousRemark(String text, String userEmail, String postId) {
        this.text = text;
        this.userEmail = "Anonymous User";
        this.postId = postId;
        this.index = String.valueOf(RemarkDemo.count++);
    }

    AnonymousRemark(String text, String userEmail, String postId, String index) {
        this.text = text;
        this.userEmail = "Anonymous User";
        this.postId = postId;
        this.index = index;
        RemarkDemo.count++;
    }
}