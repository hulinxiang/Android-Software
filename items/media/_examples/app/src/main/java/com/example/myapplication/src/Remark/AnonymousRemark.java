package com.example.myapplication.src.Remark;

import com.example.myapplication.BPlusTree.User.BPlusTreeManagerUser;

public class AnonymousRemark extends RemarkDemo {

    AnonymousRemark( String text, String userEmail, String postId) {
        this.text = text;
        this.userEmail = "Anonymous User";
        this.postId=postId;
    }

    @Override
    void updateText(String newText) {
        this.text = newText;
    }
}
