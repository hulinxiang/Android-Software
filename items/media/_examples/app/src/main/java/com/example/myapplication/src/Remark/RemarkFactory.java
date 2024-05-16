package com.example.myapplication.src.Remark;

/**
 * Author: Linxiang Hu
 */
public interface RemarkFactory {
    RemarkDemo create(String text, String userEmail, String postId);

    RemarkDemo createWithIndex(String text, String userEmail, String postId, String index);
}
