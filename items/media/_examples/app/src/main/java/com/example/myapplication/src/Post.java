package com.example.myapplication.src;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * The Post class represents a post that can be created by a user.
 * Each post has several properties including postID, userID, tag, productDisplayName, price, status, imageUrl, description, postIndexInFirebase, comments, likeIDs, and buyIDs.
 *
 * @Author Yichi Zhang, Yingxuan Tang
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

    /**
     * Constructs a new Post with the specified values.
     * This constructor is used when a user creates a post.
     *
     * @param userID the user ID of the post creator
     * @param gender the gender associated with the tag
     * @param masterCategory the master category associated with the tag
     * @param subCategory the sub category associated with the tag
     * @param articleType the article type associated with the tag
     * @param baseColour the base colour associated with the tag
     * @param season the season associated with the tag
     * @param year the year associated with the tag
     * @param usage the usage associated with the tag
     * @param productDisplayName the product display name of the post
     * @param price the price of the product in the post
     * @param status the status of the post
     * @param imageUrl the image URL of the post
     * @param description the description of the post
     * @param commentText the comment text of the post
     */
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

    /**
     * Constructs a new Post with the specified values.
     * This constructor is used to peel off firebase. All data is read from firebase.
     *
     * @param postID the post ID
     * @param userID the user ID of the post creator
     * @param gender the gender associated with the tag
     * @param masterCategory the master category associated with the tag
     * @param subCategory the sub category associated with the tag
     * @param articleType the article type associated with the tag
     * @param baseColour the base colour associated with the tag
     * @param season the season associated with the tag
     * @param year the year associated with the tag
     * @param usage the usage associated with the tag
     * @param productDisplayName the product display name of the post
     * @param price the price of the product in the post
     * @param status the status of the post
     * @param imageUrl the image URL of the post
     * @param description the description of the post
     * @param commentText the comment text of the post
     * @param postIndexInFirebase the post index in firebase
     * @param likeIDs the like IDs of the post
     * @param buyIDs the buy IDs of the post
     */
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

    /**
     * Constructs a new Post with the specified values.
     * This constructor is used for Grid layout.
     *
     * @param postID the post ID
     * @param imageUrl the image URL of the post
     * @param name the name of the product in the post
     * @param price the price of the product in the post
     * @param userID the user ID of the post creator
     * @param description the description of the post
     */
    public Post(String postID, String imageUrl, String name, double price, String userID,String description) {
        this.postID = postID;
        this.imageUrl = imageUrl;
        this.productDisplayName = name;
        this.price = price;
        this.userID = userID;
        this.description = description;
    }

    /**
     * Generates the next post index.
     *
     * @return the next post index
     */
    private static synchronized String generateNextPostIndex() {
        return String.valueOf(nextPostID++);  // Increment and return the next index
    }

    /**
     * Returns the user ID of the post creator.
     *
     * @return the user ID of the post creator
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Generates a post ID.
     *
     * @return a post ID
     */
    private static String generatePostID() {
        return UUID.randomUUID().toString();
    }

    /**
     * Parses the comments from the comment text.
     *
     * @param commentText the comment text
     * @return a list of Comment objects
     */
    private List<Comment> parseComments(String commentText) {
        return Arrays.stream(commentText.split("\\\\n"))
                .map(String::trim)
                .map(Comment::new)
                .collect(Collectors.toList());
    }

    // Getters and Setters

    /**
     * Returns the post ID.
     *
     * @return the post ID
     */
    public String getPostID() {
        return postID;
    }

    /**
     * Returns the tag of the post.
     *
     * @return the tag of the post
     */
    public Tag getTag() {
        return tag;
    }

    /**
     * Sets the tag of the post.
     *
     * @param tag the tag to set
     */
    public void setTag(Tag tag) {
        this.tag = tag;
    }

    /**
     * Returns the product display name of the post.
     *
     * @return the product display name of the post
     */
    public String getProductDisplayName() {
        return productDisplayName;
    }

    /**
     * Sets the product display name of the post.
     *
     * @param productDisplayName the product display name to set
     */
    public void setProductDisplayName(String productDisplayName) {
        this.productDisplayName = productDisplayName;
    }

    /**
     * Returns the price of the product in the post.
     *
     * @return the price of the product in the post
     */
    public double getPrice() {
        return price;
    }

    /**
     * Returns the status of the post.
     *
     * @return the status of the post
     */
    public String getStatus() {
        return status;
    }

    /**
     * Returns the image URL of the post.
     *
     * @return the image URL of the post
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Returns the description of the post.
     *
     * @return the description of the post
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the comments of the post.
     *
     * @return the comments of the post
     */
    public String getComments() {
        return comments;
    }

    /**
     * Returns the post index in firebase.
     *
     * @return the post index in firebase
     */
    public String getPostIndexInFirebase() {
        return postIndexInFirebase;
    }
}