package com.example.myapplication.src.Divider.CommentsDivider;

import com.example.myapplication.src.Divider.Divider;

public class CommentsDivider extends Divider<String> {
    @Override
    protected String getDivider() {
        return "\\|\\|";
    }

    @Override
    protected void processResult(String[] result) {
        // 对评论数据进行后处理，例如去除首尾空格
        for (int i = 0; i < result.length; i++) {
            result[i] = result[i].trim();
        }
    }
}
