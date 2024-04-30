package com.example.myapplication.src;

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.List;

/**
 * 这个类用于管理商品信息。
 *
 * 职责:
 *
 * 存储商品的详细信息，如商品名、描述、价格、图片URLs、发布者等。
 * 方法可能包括设置和获取商品属性。
 */
public class Post {

    private String postId;
    private String tag;
    private String topic;
    private double price;
    private String seller;
    private int like;
    private int status; // 1.无人订 2.有人订 3.有人暂订
    private String description;
    private String imageUrl;
    private List<Comment> comments = new ArrayList<>();

    // 构造函数、getter和setter方法

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

}
