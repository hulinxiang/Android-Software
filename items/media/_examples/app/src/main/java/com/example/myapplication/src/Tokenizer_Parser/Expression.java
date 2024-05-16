package com.example.myapplication.src.Tokenizer_Parser;


/**
 * @author Linxiang Hu u7633783
 * Represents a full expression that combines multiple components, typically including numbers and operators.
 * This class extends ResultsShow to compose and display expressions from individual parts.
 */
public class Expression extends ResultsShow {

    // Holds the left part of the expression, typically a number.
    ResultsShow leftNum;
    // Holds the middle part of the expression, typically an operator or dot.
    ResultsShow dot;
    // Holds the right part of the expression, typically a number.
    ResultsShow rightNum;

    /**
     * Constructs an Expression object with specified components.
     * @param leftNum The left part of the expression as a ResultsShow.
     * @param dot The operator or dot in the middle as a ResultsShow.
     * @param rightNum The right part of the expression as a ResultsShow.
     */
    public Expression(ResultsShow leftNum, ResultsShow dot, ResultsShow rightNum) {
        // Initialize the left part of the expression.
        this.leftNum = leftNum;
        // Initialize the dot or operator in the middle.
        this.dot = dot;
        // Initialize the right part of the expression.
        this.rightNum = rightNum;
    }

    /**
     * Overrides the show method to concatenate and format the parts of the expression into a single string.
     * @return The concatenated and formatted string representation of the entire expression.
     */
    @Override
    public String show() {
        // Combine and return the display of all parts.
        return leftNum.show() + dot.show() + rightNum.show();
    }
}