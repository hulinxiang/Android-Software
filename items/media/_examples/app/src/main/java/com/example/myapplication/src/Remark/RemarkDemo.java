package com.example.myapplication.src.Remark;

/**
 * @author Linxiang Hu
 * An abstract class that represents a remark or comment associated with a specific post.
 * It includes attributes for the remark's content, the user's email, the post ID, and an index for ordering.
 */
public abstract class RemarkDemo {
    // The text content of the remark.
    public String text;
    // The email address of the user who made the remark.
    public String userEmail;
    // The identifier of the post associated with the remark.
    public String postId;
    // The index of the remark in firebase.
    public String index;
    // A static counter to keep track of all remark instances created.
    public static int count=0;

    /**
     * Gets the text content of the remark.
     * @return The text of the remark.
     */
    public String getText() {
        // Return the text content of the remark.
        return text;
    }

    /**
     * Gets the email address of the user who made the remark.
     * @return The user's email address.
     */
    public String getUserEmail() {
        // Return the email address of the user.
        return userEmail;
    }

    /**
     * Sets the text content of the remark.
     * @param text The new text content for the remark.
     */
    public void setText(String text) {
        this.text = text;// Update the text content of the remark.
    }

    /**
     * Gets the post ID associated with the remark.
     * @return The post ID.
     */
    public String getPostId() {
        // Return the post identifier.
        return postId;
    }

    /**
     * Sets the post ID associated with the remark.
     * @param postId The new post ID for the remark.
     */
    public void setPostId(String postId) {
        // Update the post identifier.
        this.postId = postId;
    }

    /**
     * Gets the index of the remark in firebase.
     * @return The index of the remark.
     */
    public String getIndex() {
        // Return the index of the remark.
        return index;
    }

    /**
     * Sets the index of the remark in firebase.
     * @param index The new index for the remark.
     */
    public void setIndex(String index) {
        // Update the index.
        this.index = index;
    }
}