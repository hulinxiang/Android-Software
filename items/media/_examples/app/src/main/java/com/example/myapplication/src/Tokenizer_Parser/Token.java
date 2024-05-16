package com.example.myapplication.src.Tokenizer_Parser;


/**
 * @author Linxiang Hu
 * <p>
 * Represents a token extracted by the tokenizer. This class holds the token's value and its type.
 */
public class Token {

    /**
     * Defines the types of tokens that can be recognized.
     */
    public enum Type {
        Number,// Represents numeric tokens.
        Dot // Represents a dot (.) token.
    }


    /**
     * Exception class for handling illegal types or unexpected characters during tokenization.
     */
    public static class IllegalTypeException extends IllegalArgumentException {
        /**
         * Constructs an IllegalTypeException with the given message.
         *
         * @param text The message explaining what caused the exception.
         */
        public IllegalTypeException(String text) {
            super(text);// Calls the superclass constructor with the provided text.
        }
    }

    private final String token; // Holds the value of the token.
    private final Type type; // Holds the type of the token (Number or Dot).

    /**
     * Constructs a Token with a specified string value and type.
     *
     * @param token The string value of the token.
     * @param type  The type of the token (Number or Dot).
     */

    public Token(String token, Type type) {
        this.token = token; // Initialize the token with the provided string value.
        this.type = type; // Initialize the type with the provided type.
    }


    /**
     * Returns the value of the token.
     *
     * @return the string value of the token.
     */
    public String getToken() {
        return token;// Return the value of the token.
    }

    /**
     * Returns the type of the token.
     *
     * @return the type of the token (Number or Dot).
     */
    public Type getType() {
        return type;// Return the type of the token.
    }
}