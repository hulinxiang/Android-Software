package com.example.myapplication.src.Tokenizer_Parser;

public class Parser {
    Tokenizer tokenizer;

    public Parser(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }


    public static class IllegalFormatException extends IllegalArgumentException {
        public IllegalFormatException(String errorMessage) {
            super(errorMessage);
        }
    }

    public ResultsShow parse() {
        if (tokenizer.current().getType() != Token.Type.Number) {
            throw new IllegalFormatException("The format is invalid");
        }
        ResultsShow left = new Num(tokenizer.current().getToken());
        tokenizer.proceed();
        ResultsShow dot = new ResultsShow();
        ResultsShow right = new ResultsShow();
        int count = 0;
        while (tokenizer.hasNext()) {
            if (tokenizer.current().getType() == Token.Type.Dot) {
                dot = new Dot();
                tokenizer.proceed();
            } else if (tokenizer.current().getType() == Token.Type.Number) {
                right = new Num(tokenizer.current().getToken());
                tokenizer.proceed();
            }
            count++;
        }
        if (count > 2 || count == 1) {
            throw new IllegalFormatException("The format is invalid");
        }

        return new Expression(left, dot, right);
    }


}
