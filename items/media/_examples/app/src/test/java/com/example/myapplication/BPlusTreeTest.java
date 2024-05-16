package com.example.myapplication;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

import com.example.myapplication.BPlusTree.BPlusTree;

/**
 * This class contains unit tests for the BPlusTree class.
 * It tests the main operations of a B+ tree, such as insert, query, range query, update, and remove.
 *
 * @author Yichi Zhang u7748799
 */
public class BPlusTreeTest {

    private BPlusTree<Integer, String> tree;

    /**
     * This method sets up the B+ tree with a degree of 3 before each test.
     */
    @Before
    public void setUp() {
        tree = new BPlusTree<>(3);
    }

    /**
     * This test checks the insert operation of the B+ tree.
     * It inserts several key-value pairs and then checks if the data in the tree is as expected.
     */
    @Test
    public void testInsert() {
        tree.insert(5, "E");
        tree.insert(3, "C");
        tree.insert(7, "G");
        tree.insert(1, "A");
        tree.insert(9, "I");
        tree.insert(3, "B");
        tree.insert(7, "H");
        tree.insert(3, "D");
        tree.insert(5, "F");

        List<String> expected = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I");
        List<String> actual = tree.queryAllData();

        assertEquals(expected, actual);
    }

    /**
     * This test checks the query operation of the B+ tree.
     * It inserts several key-value pairs and then checks if the query result for a specific key is as expected.
     */
    @Test
    public void testQuery() {
        tree.insert(5, "E");
        tree.insert(3, "C");
        tree.insert(7, "G");
        tree.insert(3, "B");
        tree.insert(3, "D");
        tree.insert(5, "F");

        List<String> expected = Arrays.asList("B", "C", "D");
        List<String> actual = tree.query(3);

        assertEquals(expected, actual);
    }

    /**
     * This test checks the range query operation of the B+ tree.
     * It inserts several key-value pairs and then checks if the range query result is as expected.
     */
    @Test
    public void testRangeQuery() {
        tree.insert(5, "E");
        tree.insert(3, "C");
        tree.insert(7, "G");
        tree.insert(1, "A");
        tree.insert(9, "I");
        tree.insert(3, "B");
        tree.insert(7, "H");
        tree.insert(3, "D");
        tree.insert(5, "F");

        List<String> expected = Arrays.asList("B","C", "D", "E", "F", "G", "H");
        List<String> actual = tree.rangeQuery(3, 8);

        assertEquals(expected, actual);
    }

    /**
     * This test checks the update operation of the B+ tree.
     * It inserts several key-value pairs, updates a value for a specific key, and then checks if the updated value is as expected.
     */
    @Test
    public void testUpdate() {
        tree.insert(5, "E");
        tree.insert(3, "C");
        tree.insert(7, "G");

        boolean updated = tree.update(3, "C", "B");
        assertTrue(updated);

        List<String> expected = Arrays.asList("B");
        List<String> actual = tree.query(3);

        assertEquals(expected, actual);
    }

    /**
     * This test checks the remove operation of the B+ tree.
     * It inserts several key-value pairs, removes a value for a specific key, and then checks if the removed value is no longer in the tree.
     */
    @Test
    public void testRemove() {
        tree.insert(5, "E");
        tree.insert(3, "C");
        tree.insert(7, "G");
        tree.insert(3, "B");
        tree.insert(3, "D");
        tree.insert(5, "F");

        boolean removed = tree.remove(5, "E");
        assertTrue(removed);

        List<String> expected = Arrays.asList("F");
        List<String> actual = tree.query(5);

        assertEquals(expected, actual);
    }

    /**
     * This test checks the remove all operation of the B+ tree.
     * It inserts several key-value pairs, removes all values for a specific key, and then checks if no values for that key are left in the tree.
     */
    @Test
    public void testRemoveAll() {
        tree.insert(5, "E");
        tree.insert(3, "C");
        tree.insert(7, "G");
        tree.insert(3, "B");
        tree.insert(3, "D");
        tree.insert(5, "F");

        boolean removed = tree.remove(3);
        assertTrue(removed);

        List<String> expected = Arrays.asList();
        List<String> actual = tree.query(3);

        assertEquals(expected, actual);
    }
}