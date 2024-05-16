package com.example.myapplication.src.Tokenizer_Parser;

/**
 * Author: Linxiang Hu
 */
public class Expression extends ResultsShow {
    ResultsShow leftNum;
    ResultsShow dot;
    ResultsShow rightNum;

    public Expression(ResultsShow leftNum, ResultsShow dot, ResultsShow rightNum) {
        this.leftNum = leftNum;
        this.dot = dot;
        this.rightNum = rightNum;
    }

    @Override
    public String show() {
        return leftNum.show() + dot.show() + rightNum.show();
    }
}
