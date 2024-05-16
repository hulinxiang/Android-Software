package com.example.myapplication.src;

/**
 * The Comment class represents a comment that can be associated with a post.
 * Each comment has a text property that represents the content of the comment.
 *
 * @Author Yichi Zhang u7748799
 */
public class Comment {
    private String text;

    /**
     * Constructs a new Comment with the specified text.
     *
     * @param text the text of the comment
     */
    public Comment(String text) {
        this.text = text;
    }

    /**
     * Returns the text of the comment.
     *
     * @return the text of the comment
     */
    public String getComment() {
        return text;
    }

    /**
     * Sets the text of the comment.
     *
     * @param text the text to set
     */
    public void setComment(String text) {
        this.text = text;
    }
}