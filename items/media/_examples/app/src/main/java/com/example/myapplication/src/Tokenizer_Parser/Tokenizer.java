package com.example.myapplication.src.Tokenizer_Parser;

public class Tokenizer {
    private String buffer;
    private Token currentToken;

    public Tokenizer(String text) {
        buffer = text;
        proceed();
    }

    public void proceed() {
        buffer = buffer.trim();
        if (buffer.isEmpty()) {
            currentToken = null;
            return;
        }
        char firstChar = buffer.charAt(0);
        if (Character.isDigit(firstChar)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(firstChar);
            for (int i = 1; i < buffer.length(); i++) {
                if (Character.isDigit(buffer.charAt(i))) {
                    stringBuilder.append(buffer.charAt(i));
                } else {
                    break;
                }
            }
            currentToken = new Token(stringBuilder.toString(), Token.Type.Number);
        } else if (firstChar == '.') {
            currentToken = new Token(".", Token.Type.Dot);
        } else {
            throw new Token.IllegalTypeException("The input is invalid");
        }

        int tokenLen = currentToken.getToken().length();
        buffer = buffer.substring(tokenLen);
    }


    public Token current() {
        return currentToken;
    }

    public boolean hasNext() {
        return currentToken != null;
    }

}
