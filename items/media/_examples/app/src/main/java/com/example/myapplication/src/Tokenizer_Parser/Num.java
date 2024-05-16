package com.example.myapplication.src.Tokenizer_Parser;

/**
 * Author: Linxiang Hu
 */
public class Num extends ResultsShow {

    String num;

    public Num(String num) {
        this.num = num;
    }

    @Override
    public String show() {
        return num + "|";
    }
}
