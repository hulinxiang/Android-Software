package com.example.myapplication.src.Tokenizer_Parser;

/**
 * @author Linxiang Hu
 * <p>
 * A simple tokenizer class for parsing strings into tokens based on numeric and dot characters.
 */
public class Tokenizer {

    // Holds the text that needs to be tokenized.
    private String buffer;

    // Holds the current token extracted from the buffer.
    private Token currentToken;

    /**
     * Constructs a tokenizer with the given text.
     *
     * @param text the string to tokenize.
     */

    public Tokenizer(String text) {
        // Initialize the buffer with the input text.
        buffer = text;
        // Start tokenizing by finding the first token.
        proceed();
    }


    /**
     * Proceeds to the next token in the buffer.
     */
    public void proceed() {
        // Trim leading and trailing whitespace from the buffer.
        buffer = buffer.trim();
        // Check if the buffer is empty after trimming.
        if (buffer.isEmpty()) {
            // Set currentToken to null if buffer is empty.
            currentToken = null;
            // Exit the method as there are no more tokens.
            return;
        }
        // Get the first character of the trimmed buffer.
        char firstChar = buffer.charAt(0);
        // Check if the first character is a digit.
        if (Character.isDigit(firstChar)) {
            // Create a StringBuilder to build the number token.
            StringBuilder stringBuilder = new StringBuilder();
            // Append the first character to the stringBuilder.
            stringBuilder.append(firstChar);
            // Iterate over the remaining characters in the buffer.
            for (int i = 1; i < buffer.length(); i++) {
                // Check if the current character is a digit.
                if (Character.isDigit(buffer.charAt(i))) {
                    // Append the digit to the stringBuilder.
                    stringBuilder.append(buffer.charAt(i));
                } else {
                    // Break the loop if a non-digit is encountered.
                    break;
                }
            }
            // Create a new token of type Number from the stringBuilder content.
            currentToken = new Token(stringBuilder.toString(), Token.Type.Number);
        }
        // Check if the first character is a dot.
        else if (firstChar == '.') {
            // Create a new token of type Dot.
            currentToken = new Token(".", Token.Type.Dot);
        } else {
            // Throw an exception if the character isn't a digit or dot.
            throw new Token.IllegalTypeException("The input is invalid");
        }
        // Calculate the length of the current token.
        int tokenLen = currentToken.getToken().length();
        // Remove the current token from the buffer.
        buffer = buffer.substring(tokenLen);
    }

    /**
     * Returns the current token.
     * @return the current token.
     */
    public Token current() {
        // Return the current token.
        return currentToken;
    }


    /**
     * Checks if there are more tokens available.
     * @return true if there is a next token, false otherwise.
     */
    public boolean hasNext() {
        // Return true if currentToken is not null, indicating more tokens are available.
        return currentToken != null;
    }
}