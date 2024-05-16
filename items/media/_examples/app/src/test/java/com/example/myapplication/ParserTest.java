package com.example.myapplication;

import com.example.myapplication.src.Tokenizer_Parser.Parser;
import com.example.myapplication.src.Tokenizer_Parser.ResultsShow;
import com.example.myapplication.src.Tokenizer_Parser.Token;
import com.example.myapplication.src.Tokenizer_Parser.Tokenizer;

import org.junit.Assert;
import org.junit.Test;

/**
 * This class is used to test the functionality of the Parser and Tokenizer classes.
 * It tests the parsing and tokenizing of valid and invalid inputs.
 *
 * @author Yichi Zhang u7748799
 */
public class ParserTest {

    /**
     * This test case tests the parsing of a valid input with a decimal point.
     * It verifies that the parsed result matches the expected output.
     */
    @Test
    public void testValidInput() {
        String input = "123.456";
        Tokenizer tokenizer = new Tokenizer(input);
        Parser parser = new Parser(tokenizer);
        ResultsShow result = parser.parse();
        Assert.assertEquals("123|.|456|", result.show());
    }

    /**
     * This test case tests the parsing of a valid input without a decimal point.
     * It verifies that the parsed result matches the expected output.
     */
    @Test
    public void testValidInputWithoutDecimal() {
        String input = "789";
        Tokenizer tokenizer = new Tokenizer(input);
        Parser parser = new Parser(tokenizer);
        ResultsShow result = parser.parse();
        Assert.assertEquals("789|", result.show());
    }

    /**
     * This test case tests the parsing of an invalid input format.
     * It expects an IllegalFormatException to be thrown.
     */
    @Test(expected = Parser.IllegalFormatException.class)
    public void testInvalidInputFormat() {
        String input = "123.456.789";
        Tokenizer tokenizer = new Tokenizer(input);
        Parser parser = new Parser(tokenizer);
        parser.parse();
    }

    /**
     * This test case tests the parsing of a valid input without a right part after the decimal point.
     * It expects an IllegalFormatException to be thrown.
     */
    @Test(expected = Parser.IllegalFormatException.class)
    public void testValidInputWithoutRightPart() {
        String input = "123.";
        Tokenizer tokenizer = new Tokenizer(input);
        Parser parser = new Parser(tokenizer);
        parser.parse();
    }

    /**
     * This test case tests the parsing of a valid input without a left part before the decimal point.
     * It expects an IllegalFormatException to be thrown.
     */
    @Test(expected = Parser.IllegalFormatException.class)
    public void testValidInputWithoutLeftPart() {
        String input = ".456";
        Tokenizer tokenizer = new Tokenizer(input);
        Parser parser = new Parser(tokenizer);
        parser.parse();
    }

    /**
     * This test case tests the parsing of a single dot input.
     * It expects an IllegalFormatException to be thrown.
     */
    @Test(expected = Parser.IllegalFormatException.class)
    public void testSingleDotInput() {
        String input = ".";
        Tokenizer tokenizer = new Tokenizer(input);
        Parser parser = new Parser(tokenizer);
        parser.parse();
    }

    /**
     * This test case tests the tokenizing of an invalid input character.
     * It expects an IllegalTypeException to be thrown.
     */
    @Test(expected = Token.IllegalTypeException.class)
    public void testInvalidInputCharacter() {
        String input = "abc";
        Tokenizer tokenizer = new Tokenizer(input);
        tokenizer.proceed();
    }

    /**
     * This test case tests the parsing of an empty input.
     * It expects a NullPointerException to be thrown.
     */
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