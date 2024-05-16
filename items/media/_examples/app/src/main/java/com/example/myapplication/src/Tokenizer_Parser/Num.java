package com.example.myapplication.src.Tokenizer_Parser;

/**
 * @author Linxiang Hu
 * Represents a numeric token for display purposes.
 * This class extends ResultsShow to specifically handle numeric results and formats them accordingly.
 */
public class Num extends ResultsShow {

    // Holds the numeric value as a string.
    String num;

    /**
     * Constructs a Num object with the specified numeric string.
     * @param num The string representation of the numeric value.
     */
    public Num(String num) {
        // Initialize the num field with the provided string value.
        this.num = num;
    }

    /**
     * Overrides the show method to provide a specific representation of the numeric token.
     * This method appends a vertical bar "|" to the numeric string.
     * @return The formatted string representation of the numeric token.
     */
    @Override
    public String show() {
        // Return the numeric string appended with a vertical bar.
        return num + "|";
    }
}
