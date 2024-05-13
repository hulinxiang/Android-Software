package com.example.myapplication;

import com.example.myapplication.src.Divider.CommentsDivider.CommentsDivider;
import com.example.myapplication.src.Divider.Divider;
import com.example.myapplication.src.Divider.IDsDivider.IDsDivider;

import org.junit.Assert;
import org.junit.Test;

public class TemplateMethodDesignPatternTest {
    @Test
    public void testCommentsDivider() {
        String input = "Hello    || World || Java";
        String[] expected = {"Hello", "World", "Java"};

        String[] result = Divider.divide(input, new CommentsDivider());

        Assert.assertArrayEquals(expected, result);
    }

    @Test
    public void testIDsDivider() {
        String input = "1,2,3,4,5";
        String[] expected = {"1", "2", "3", "4", "5"};

        String[] result = Divider.divide(input, new IDsDivider());

        Assert.assertArrayEquals(expected, result);
    }
}
