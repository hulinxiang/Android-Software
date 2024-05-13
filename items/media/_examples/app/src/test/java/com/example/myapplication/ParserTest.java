package com.example.myapplication;
import com.example.myapplication.src.Tokenizer_Parser.Parser;
import com.example.myapplication.src.Tokenizer_Parser.ResultsShow;
import com.example.myapplication.src.Tokenizer_Parser.Token;
import com.example.myapplication.src.Tokenizer_Parser.Tokenizer;

import org.junit.Assert;
import org.junit.Test;

public class ParserTest {

    @Test
    public void testValidInput() {
        String input = "123.456";
        Tokenizer tokenizer = new Tokenizer(input);
        Parser parser = new Parser(tokenizer);
        ResultsShow result = parser.parse();
        Assert.assertEquals("(123)(.)(456)", result.show());
    }

    @Test
    public void testValidInputWithoutDecimal() {
        String input = "789";
        Tokenizer tokenizer = new Tokenizer(input);
        Parser parser = new Parser(tokenizer);
        ResultsShow result = parser.parse();
        Assert.assertEquals("(789)", result.show());
    }

    @Test(expected = Parser.IllegalFormatException.class)
    public void testInvalidInputFormat() {
        String input = "123.456.789";
        Tokenizer tokenizer = new Tokenizer(input);
        Parser parser = new Parser(tokenizer);
        parser.parse();
    }

    @Test(expected = Parser.IllegalFormatException.class)
    public void testValidInputWithoutRightPart() {
        String input = "123.";
        Tokenizer tokenizer = new Tokenizer(input);
        Parser parser = new Parser(tokenizer);
        parser.parse();
    }

    @Test(expected = Parser.IllegalFormatException.class)
    public void testValidInputWithoutLeftPart() {
        String input = ".456";
        Tokenizer tokenizer = new Tokenizer(input);
        Parser parser = new Parser(tokenizer);
        parser.parse();
    }

    @Test(expected = Parser.IllegalFormatException.class)
    public void testSingleDotInput() {
        String input = ".";
        Tokenizer tokenizer = new Tokenizer(input);
        Parser parser = new Parser(tokenizer);
        parser.parse();
    }

    @Test(expected = Token.IllegalTypeException.class)
    public void testInvalidInputCharacter() {
        String input = "abc";
        Tokenizer tokenizer = new Tokenizer(input);
        tokenizer.proceed();
    }

    @Test
    public void testEmptyInputWithException() {
        String input = "";
        Tokenizer tokenizer = new Tokenizer(input);
        Parser parser = new Parser(tokenizer);

        try {
            parser.parse();
            Assert.fail("Expected NullPointerException to be thrown");
        } catch (NullPointerException e) {
            // Expected exception, test passes
        }
    }
}