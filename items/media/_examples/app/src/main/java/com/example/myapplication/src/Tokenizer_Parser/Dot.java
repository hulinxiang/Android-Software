package com.example.myapplication.src.Tokenizer_Parser;

/**
 *
 * @author Linxiang Hu u7633783
 * Represents the dot character as a part of an expression.
 * This class extends ResultsShow to handle the display of the dot symbol in a formatted manner.
 */
public class Dot extends ResultsShow {
    // Holds the dot character, predefined as ".".
    String dot = ".";


    /**
     * Overrides the show method to provide a specific representation of the dot.
     * This method appends a vertical bar "|" to the dot character.
     * @return The formatted string representation of the dot character.
     */
    public String show() {
        // Return the dot character appended with a vertical bar.
        return dot + "|";
    }


}
