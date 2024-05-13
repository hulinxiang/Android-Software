package com.example.myapplication.src.Tokenizer_Parser;

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
