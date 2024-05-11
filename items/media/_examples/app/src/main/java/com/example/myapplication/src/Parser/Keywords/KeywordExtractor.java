package com.example.myapplication.src.Parser.Keywords;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;

public class KeywordExtractor {
//    public static List<String> extractKeyWords(String text) {
//        // 创建词法分析器和解析器
//        CharStream stream = CharStreams.fromString(text);
//        KeywordsLexer lexer = new KeywordsLexer(stream);
//        CommonTokenStream tokens = new CommonTokenStream(lexer);
//        KeywordsParser parser = new KeywordsParser(tokens);
//        ParseTree tree = parser.text(); // 根据你的 start 规则
//
//        // 遍历 AST，提取词汇
//        List<String> keywords = new ArrayList<>();
//        ParseTreeWalker walker = new ParseTreeWalker();
//        walker.walk(new KeywordsListener() {
//            @Override
//            public void visitTerminal(TerminalNode node) {
//
//            }
//
//            @Override
//            public void visitErrorNode(ErrorNode node) {
//
//            }
//
//            @Override
//            public void enterEveryRule(ParserRuleContext ctx) {
//
//            }
//
//            @Override
//            public void exitEveryRule(ParserRuleContext ctx) {
//
//            }
//
//            @Override
//            public void enterStart(KeywordsParser.StartContext ctx) {
//
//            }
//
//            @Override
//            public void exitStart(KeywordsParser.StartContext ctx) {
//
//            }
//
//            @Override
//            public void enterText(KeywordsParser.TextContext ctx) {
//
//            }
//
//            @Override
//            public void exitText(KeywordsParser.TextContext ctx) {
//
//            }
//        }, tree);
//        return keywords;

//        CharStream input = CharStreams.fromString(text);
//        KeywordsLexer lexer = new KeywordsLexer(input);
//        CommonTokenStream tokens = new CommonTokenStream(lexer);
//
//        List<String> nouns = new ArrayList<>();
//        List<String> verbs = new ArrayList<>();
//        for (Token t : tokens.getTokens()) {
//            if (t.getType() == KeywordsLexer.NOUN) {
//                nouns.add(t.getText());
//            } else if (t.getType() == KeywordsLexer.VERB) {
//                verbs.add(t.getText());
//            }
//        }
//
//        List<String> combined = new ArrayList<>();
//        combined.addAll(nouns);
//        combined.addAll(verbs);
//        return combined;

    //        List<String> keywords = new ArrayList<>();
//        for (Token t : tokens.getTokens()) {
//            if (t.getType() == KeywordsLexer.WORD) {
//                keywords.add(t.getText());
//            }
//        }
//
//        return keywords;
//    }
    public List<String> extractKeyWords(String input) {
        // 初始化词法和语法分析器
        CharStream stream = CharStreams.fromString(input);
        KeywordsLexer lexer = new KeywordsLexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KeywordsParser parser = new KeywordsParser(tokens);
        ParseTree tree = parser.text(); // 根据你的 start 规则解析输入

        // 创建并使用自定义 Listener
        KeywordsExtractorListener listener = new KeywordsExtractorListener();
        ParseTreeWalker.DEFAULT.walk(listener, tree);

        // 返回提取的关键词
        return listener.getKeywords();
    }

    class KeywordsExtractorListener extends KeywordsBaseListener {
        private List<String> keywords = new ArrayList<>();

        @Override
        public void visitTerminal(TerminalNode node) {
            String text = node.getText();
            int type = node.getSymbol().getType();

            // 根据你的 ANTLR 生成的词法符号类判断类型
            if (type == KeywordsLexer.NOUN || type == KeywordsLexer.VERB) {
                keywords.add(text);
            }
        }

        public List<String> getKeywords() {
            return keywords;
        }
    }


}
