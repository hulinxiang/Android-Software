package com.example.myapplication.src;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Author: Yichi Zhang, Yingxuan Tang
 */
public class Post {
    private static int nextPostID = 1000;
    private String postID;
    private String userID;
    private Tag tag;
    private String productDisplayName;
    private double price;
    private String status;
    private String imageUrl;
    private String description;
    private String postIndexInFirebase;
    private String comments;
    private String likeIDs;
    private String buyIDs;

    // Constructor

    // When the user creates a post
    public Post(String userID, String gender, String masterCategory, String subCategory, String articleType,
                String baseColour, String season, int year, String usage, String productDisplayName,
                double price, String status, String imageUrl, String description, String commentText)  {
        this.postID = generatePostID();
        this.userID = userID;
        this.tag = new Tag(gender, masterCategory, subCategory, articleType, baseColour, season, year, usage);
        this.productDisplayName = productDisplayName;
        this.price = price;
        this.status = status;
        this.imageUrl = imageUrl;
        this.description = description;
        this.comments = commentText;
        this.postIndexInFirebase = generateNextPostIndex();
        this.likeIDs = "";
        this.buyIDs = "";
    }

    // A full-parameter constructor used to peel off firebase. All data is read from firebase
    public Post(String postID, String userID, String gender, String masterCategory, String subCategory, String articleType,
                String baseColour, String season, int year, String usage, String productDisplayName,
                double price, String status, String imageUrl, String description, String commentText, String postIndexInFirebase, String likeIDs, String buyIDs)  {
        this.postID = postID;
        this.userID = userID;
        this.tag = new Tag(gender, masterCategory, subCategory, articleType, baseColour, season, year, usage);
        this.productDisplayName = productDisplayName;
        this.price = price;
        this.status = status;
        this.imageUrl = imageUrl;
        this.description = description;
        this.comments = commentText;
        this.postIndexInFirebase = postIndexInFirebase;
        this.likeIDs = likeIDs;
        this.buyIDs = buyIDs;
    }

    // Constructor for Grid layout
    public Post(String postID, String imageUrl, String name, double price, String userID,String description) {
        this.postID = postID;
        this.imageUrl = imageUrl;
        this.productDisplayName = name;
        this.price = price;
        this.userID = userID;
        this.description = description;
    }

    private static synchronized String generateNextPostIndex() {
        return String.valueOf(nextPostID++);  // Increment and return the next index
    }


    public String getUserID() {
        return userID;
    }


    private static String generatePostID() {
        return UUID.randomUUID().toString();
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

    public String getStatus() {
        return status;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public String getComments() {
        return comments;
    }

    public String getPostIndexInFirebase() {
        return postIndexInFirebase;
    }

}