package com.example.myapplication.src;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Post {
    private static int nextPostID = 1000;

    private String userID;
    private String postID;
    private Tag tag;
    private String productDisplayName;
    private double price;
    private String status;
    private String imageUrl;
    private String description;
    private List<Comment> comments;

    public Post(String userID, String gender, String masterCategory, String subCategory, String articleType,
                String baseColour, String season, int year, String usage, String productDisplayName,
                double price, String status, String imageUrl, String description, String commentText)  {
        this.userID = userID;
        this.postID = generatePostID();
//        this.tag = new Tag(gender, new Tag.MasterCategory(masterCategory, new Tag.SubCategory(subCategory, new Tag.ArticleType(articleType))), baseColour, season, year, usage);
        this.tag = new Tag(gender, masterCategory, subCategory, articleType, baseColour, season, year, usage);
        this.productDisplayName = productDisplayName;
        this.price = price;
        this.status = status;
        this.imageUrl = imageUrl;
        this.description = description;
        this.comments = parseComments(commentText);
    }

    public Post(String userID, String postID, String gender, String masterCategory, String subCategory, String articleType,
                String baseColour, String season, int year, String usage, String productDisplayName,
                double price, String status, String imageUrl, String description, String commentText)  {
        this.userID = userID;
        this.postID = getPostID();
//        this.tag = new Tag(gender, new Tag.MasterCategory(masterCategory, new Tag.SubCategory(subCategory, new Tag.ArticleType(articleType))), baseColour, season, year, usage);
        this.tag = new Tag(gender, masterCategory, subCategory, articleType, baseColour, season, year, usage);
        this.productDisplayName = productDisplayName;
        this.price = price;
        this.status = status;
        this.imageUrl = imageUrl;
        this.description = description;
        this.comments = parseComments(commentText);
    }


    // Constructor for Grid layout
    public Post(String postID, String imageUrl, String name, double price,String userID,String description) {
        this.postID = postID;
        this.imageUrl = imageUrl;
        this.productDisplayName = name;
        this.price = price;
        this.userID = userID;
        this.description = description;
    }


    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    private static String generatePostID() {
        return String.valueOf(nextPostID++);
    }

    private List<Comment> parseComments(String commentText) {
        return Arrays.stream(commentText.split("\\\\n"))
                .map(String::trim)
                .map(Comment::new)
                .collect(Collectors.toList());
    }

    // Getters and Setters

    public String getPostID() {
        return postID;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}