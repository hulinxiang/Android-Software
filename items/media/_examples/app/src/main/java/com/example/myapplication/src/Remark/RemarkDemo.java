package com.example.myapplication.src.Remark;


public abstract class RemarkDemo {
    public String text;
    public String userEmail;
    public String postId;

    abstract void updateText(String newText);

    public String getText() {
        return text;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
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


}
