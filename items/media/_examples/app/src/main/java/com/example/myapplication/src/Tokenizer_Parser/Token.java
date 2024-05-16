package com.example.myapplication.src.Tokenizer_Parser;

/**
 * Author: Linxiang Hu
 */
public class Token {
    public enum Type {
        Number, Dot
    }

    public static class IllegalTypeException extends IllegalArgumentException {
        public IllegalTypeException(String text) {
            super(text);
        }
    }

    private final String token;

    private final Type type;

    public Token(String token, Type type) {
        this.token = token;
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public Type getType() {
        return type;
    }
}