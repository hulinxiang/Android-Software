package com.example.myapplication.src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.example.myapplication.src.Tag;
import com.example.myapplication.src.Comment;

/**
 * 这个类用于管理商品信息。
 *
 * 职责:
 *
 * 存储商品的详细信息，如商品名、描述、价格、图片URLs、发布者等。
 * 方法可能包括设置和获取商品属性。
 */
public class Post {

    private String postID;
    private Tag tag;
    private String productDisplayName;
    private double price;
    private String status;
    private String description;
    private String filename;
    private String link;
    private List<Comment> comments;

    public Post(String postID, Tag tag, String productDisplayName, double price, String status, String description, String filename, String link, String commentText) {
        this.postID = postID;
        this.tag = tag;
        this.productDisplayName = productDisplayName;
        this.price = price;
        this.status = status;
        this.description = description;
        this.filename = filename;
        this.link = link;
        this.comments = parseComments(commentText);//这里的comments我不确定是不是要通过下面的parseComments方法来解析完之后分成一个Comment组成的List来调用
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public String getProductDisplayName() {
        return productDisplayName;
    }

    public void setProductDisplayName(String productDisplayName) {
        this.productDisplayName = productDisplayName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }


    /**
     * Parses the input string for comments separated by "||" and creates a list of Comment objects.
     *
     * @param commentText The string containing all comments separated by "||".
     * @return A list of Comment objects.
     */
    private List<Comment> parseComments(String commentText) {
        return Arrays.stream(commentText.split("\\|\\|"))
                .map(String::trim)
                .map(Comment::new)
                .collect(Collectors.toList());
    }

    // Getter for comments
    public List<Comment> getComments() {
        return comments;
    }

    // Setter for comments
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

}
