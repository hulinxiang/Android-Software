package com.example.myapplication.src.Parser.Keywords;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;

import java.util.ArrayList;
import java.util.List;

public class KeywordExtractor {
    public static List<String> extractKeyWords(String text) {
        CharStream input = CharStreams.fromString(text);
        KeywordsLexer lexer = new KeywordsLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        List<String> nouns = new ArrayList<>();
        List<String> verbs = new ArrayList<>();
        for (Token t : tokens.getTokens()) {
            if (t.getType() == KeywordsLexer.NOUN) {
                nouns.add(t.getText());
            } else if (t.getType() == KeywordsLexer.VERB) {
                verbs.add(t.getText());
            }
        }

        List<String> combined = new ArrayList<>();
        combined.addAll(nouns);
        combined.addAll(verbs);
        return combined;

//        List<String> keywords = new ArrayList<>();
//        for (Token t : tokens.getTokens()) {
//            if (t.getType() == KeywordsLexer.WORD) {
//                keywords.add(t.getText());
//            }
//        }
//
//        return keywords;
    }
}
