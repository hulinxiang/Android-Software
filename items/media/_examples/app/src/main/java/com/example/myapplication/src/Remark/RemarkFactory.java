package com.example.myapplication.src.Remark;

public interface RemarkFactory {
    RemarkDemo create(String text, String userEmail, String postId);

    RemarkDemo createWithIndex(String text, String userEmail, String postId, String index);
}
