package com.example.myapplication.src.Remark;

/**
 * Author: Linxiang Hu
 */
public abstract class RemarkDemo {
    public String text;
    public String userEmail;
    public String postId;
    public String index;
    public static int count=0;

    public String getText() {
        return text;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}