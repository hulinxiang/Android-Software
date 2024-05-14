package com.example.myapplication.src;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    //用户create post时候用的
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
    //用来扒取firebase的全参构造器，全部数据从firebase读取
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

//    public Post(String postID, String userID, String gender, String masterCategory, String subCategory, String articleType, String baseColour, String season, int year, String usage, String productDisplayName, double price, String status, String imageUrl, String description, String comments, String postIndexInFirebase) {
//        this.postID = postID;
//        this.userID = userID;
//        this.tag = new Tag(gender, masterCategory, subCategory, articleType, baseColour, season, year, usage);
//        this.productDisplayName = productDisplayName;
//        this.price = price;
//        this.status = status;
//        this.imageUrl = imageUrl;
//        this.description = description;
//        this.comments = comments != null ? parseComments(Arrays.asList(comments.split(","))) : new ArrayList<>();
//        this.postIndexInFirebase = postIndexInFirebase;
//    }

//    public Post(String postID, String userID, String gender, String masterCategory, String subCategory, String articleType, String baseColour, String season, int year, String usage, String productDisplayName, double price, String status, String imageUrl, String description, String comments, String postIndexInFirebase) {
//        this.postID = postID;
//        this.userID = userID;
//        this.tag = new Tag(gender, masterCategory, subCategory, articleType, baseColour, season, year, usage);
//        this.productDisplayName = productDisplayName;
//        this.price = price;
//        this.status = status;
//        this.imageUrl = imageUrl;
//        this.description = description;
//        this.comments = comments != null ? parseComments(new ArrayList<>(Arrays.asList(comments.split(",")))) : new ArrayList<>();
//        this.postIndexInFirebase = postIndexInFirebase;
//    }


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

    public void setUserID(String userID) {
        this.userID = userID;
    }

//    private static String generatePostID() {
//        return String.valueOf(nextPostID++);
//    }

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

//    public List<Comment> getComments() {
//        return comments;
//    }
//
//    public void setComments(List<Comment> comments) {
//        this.comments = comments;
//    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getPostIndexInFirebase() {
        return postIndexInFirebase;
    }

    public void setPostIndexInFirebase(String postIndexInFirebase) {
        this.postIndexInFirebase = postIndexInFirebase;
    }

    public static int getNextPostID() {
        return nextPostID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public static void setNextPostID(int nextPostID) {
        Post.nextPostID = nextPostID;
    }

    public String getLikeIDs() {
        return likeIDs;
    }

    public void setLikeIDs(String likeIDs) {
        this.likeIDs = likeIDs;
    }

    public String getBuyIDs() {
        return buyIDs;
    }

    public void setBuyIDs(String buyIDs) {
        this.buyIDs = buyIDs;
    }

}