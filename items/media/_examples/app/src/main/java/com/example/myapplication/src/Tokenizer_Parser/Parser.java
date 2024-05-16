package com.example.myapplication.src.Tokenizer_Parser;


/**
 * @author Linxiang Hu u7633783
 * A parser that reads tokens from a Tokenizer and constructs an expression based on predefined rules.
 */
public class Parser {
    Tokenizer tokenizer;// The tokenizer from which this parser reads tokens.

    /**
     * Constructs a Parser with a specified Tokenizer.
     * @param tokenizer The tokenizer to be used for parsing tokens.
     */
    public Parser(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;// Initialize the tokenizer.
    }


    /**
     * Exception class for handling illegal format errors during parsing.
     */
    public static class IllegalFormatException extends IllegalArgumentException {
        /**
         * Constructs an IllegalFormatException with the given error message.
         * @param errorMessage The message explaining what caused the exception.
         */
        public IllegalFormatException(String errorMessage) {
            super(errorMessage);// Calls the superclass constructor with the provided error message.
        }
    }

    /**
     * Parses tokens from the tokenizer to construct an expression.
     * The method expects a specific format (e.g., Number, Dot, Number or just One Number).
     * @return A ResultsShow object that represents the parsed expression.
     * @throws IllegalFormatException if the format of the tokens does not meet the expected pattern.
     */
    public ResultsShow parse() {
        if (tokenizer.current().getType() != Token.Type.Number) {
            // Throws an exception if the first token is not a number.
            throw new IllegalFormatException("The format is invalid");
        }
        // Creates a new Num object from the current token.
        ResultsShow left = new Num(tokenizer.current().getToken());
        // Move to the next token.
        tokenizer.proceed();
        // Placeholder for the dot.
        ResultsShow dot = new ResultsShow();
        // Placeholder for the number following the dot.
        ResultsShow right = new ResultsShow();
        // Counter to track the number of tokens processed.
        int count = 0;
        // While there are more tokens to process.
        while (tokenizer.hasNext()) {
            if (tokenizer.current().getType() == Token.Type.Dot) {
                // Assigns a new Dot object if the token is a dot.
                dot = new Dot();
                // Move to the next token.
                tokenizer.proceed();
            } else if (tokenizer.current().getType() == Token.Type.Number) {
                // Assigns a new Num object if the token is a number.
                right = new Num(tokenizer.current().getToken());
                // Move to the next token.
                tokenizer.proceed();
            }
            // Increment the token count.
            count++;
        }
        if (count > 2 || count == 1) {
            // Throw an exception if token count is incorrect.
            throw new IllegalFormatException("The format is invalid");
        }
        // Return a new Expression composed of the parsed elements.
        return new Expression(left, dot, right);
    }
}