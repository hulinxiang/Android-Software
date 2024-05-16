package com.example.myapplication;

import com.example.myapplication.BPlusTree.User.BPlusTreeManagerUser;
import com.example.myapplication.activity.loginUsingBPlusTree.LoginCheckService;
import com.example.myapplication.activity.loginUsingBPlusTree.RegisterActivityBPlusTree;
import com.example.myapplication.src.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import androidx.test.InstrumentationRegistry;

/**
 * This class contains unit tests for the registration functionality.
 *
 * @author Yingxuan Tang u7670526
 */
public class RegisterActivityTest {

    private RegisterActivityBPlusTree registerActivity;
    private LoginCheckService loginCheckService;

    @Before
    public void setUp() {
        loginCheckService = new LoginCheckService(null);
        // Create a test user object
        User user1 = new User("testuser1", "testtt1@example.com", User.hashPassword("Test1234!"), "Test User 1", "Test Address 1", "1234567890", "userType1", "9989");
        User user2 = new User("testuser2", "test2@example.com", User.hashPassword("Test12345!"), "Test User 2", "Test Address 2", "0987654321", "userType2", "7678");

        // Insert the test user object into the BPlusTree
        BPlusTreeManagerUser.getTreeInstance(null).insert(user1.getEmail(), user1);
        BPlusTreeManagerUser.getTreeInstance(null).insert(user2.getEmail(), user2);
    }

    @After
    public void tearDown() {
        clearTestData();
    }

    /**
     * Clear the test data by removing the test users from the BPlusTree.
     */
    private void clearTestData() {
        // Deleting a test user
        BPlusTreeManagerUser.getTreeInstance(null).remove("test1@anu.edu.au");
        BPlusTreeManagerUser.getTreeInstance(null).remove("test2@anu.edu.au");

    }

    /**
     * Test for successful registration.
     */
    @Test
    public void testRegisterSuccess() {
        String email = "testtt1@examplee.com";
        String password = "Test123456!";

        boolean result = loginCheckService.checkValid(email, password);

        assertTrue(result);
    }

    /**
     * Test for registration failure due to duplicate username.
     */
    @Test
    public void testRegisterDuplicateUsername() {
        // Register a user first
        String email = "test2@example.com";
        String password = "Test12345!";
        User user = new User(email, User.hashPassword(password));
        BPlusTreeManagerUser.getTreeInstance(null).insert(email, user);

        // Try to register with the same username
        boolean result = loginCheckService.checkValid(email, password);

        assertFalse(result);
    }

    /**
     * Test for registration failure due to empty password.
     */
    @Test
    public void testRegisterEmptyPassword() {
        String email = "test1@example.com";
        String password = "";

        boolean result = loginCheckService.checkValid(email, password);

        assertFalse(result);
    }

    /**
     * Test for registration failure due to invalid password.
     */
    @Test
    public void testRegisterInvalidPassword() {
        String email = "testtt1@example.com";
        String password = "invalidpassword";

        boolean result = loginCheckService.checkValid(email, password);

        assertFalse(result);
    }
}