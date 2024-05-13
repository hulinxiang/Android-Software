package com.example.myapplication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.example.myapplication.src.Remark.AnonymousRemark;
import com.example.myapplication.src.Remark.AnonymousRemarkFactory;
import com.example.myapplication.src.Remark.CommonRemark;
import com.example.myapplication.src.Remark.CommonRemarkFactory;
import com.example.myapplication.src.Remark.RemarkDemo;
import com.example.myapplication.src.Remark.RemarkFactory;

import org.junit.Test;

public class FactoryDesignPatternTest {
    @Test
    public void testCommonRemarkFactory() {
        RemarkFactory factory = new CommonRemarkFactory();
        RemarkDemo remark = factory.create("Hello", "user@example.com", "post123");

        assertTrue(remark instanceof CommonRemark);
        assertEquals("Hello", remark.getText());
        assertEquals("user@example.com", remark.getUserEmail());
        assertEquals("post123", remark.getPostId());
    }

    @Test
    public void testAnonymousRemarkFactory() {
        RemarkFactory factory = new AnonymousRemarkFactory();
        RemarkDemo remark = factory.create("Hi there", "user@example.com", "post456");

        assertTrue(remark instanceof AnonymousRemark);
        assertEquals("Hi there", remark.getText());
        assertEquals("Anonymous User", remark.getUserEmail());
        assertEquals("post456", remark.getPostId());
    }
}
