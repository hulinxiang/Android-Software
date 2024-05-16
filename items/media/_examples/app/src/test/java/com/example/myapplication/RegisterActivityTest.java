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
 * Author: Yingxuan Tang
 */
public class RegisterActivityTest {

    private RegisterActivityBPlusTree registerActivity;
    private LoginCheckService loginCheckService;

    @Before
    public void setUp() {
        loginCheckService = new LoginCheckService(null);
        // 创建测试用户对象
        User user1 = new User("testuser1", "testtt1@example.com", User.hashPassword("Test1234!"), "Test User 1", "Test Address 1", "1234567890", "userType1", "9989");
        User user2 = new User("testuser2", "test2@example.com", User.hashPassword("Test12345!"), "Test User 2", "Test Address 2", "0987654321", "userType2", "7678");

        // 将测试用户对象插入到 BPlusTree 中
        BPlusTreeManagerUser.getTreeInstance(null).insert(user1.getEmail(), user1);
        BPlusTreeManagerUser.getTreeInstance(null).insert(user2.getEmail(), user2);
    }

    @After
    public void tearDown() {
        clearTestData();
    }

    private void clearTestData() {
        // 删除测试用户
        BPlusTreeManagerUser.getTreeInstance(null).remove("test1@anu.edu.au");
        BPlusTreeManagerUser.getTreeInstance(null).remove("test2@anu.edu.au");

    }

    @Test
    public void testRegisterSuccess() {
        String email = "testtt1@examplee.com";
        String password = "Test123456!";

        boolean result = loginCheckService.checkValid(email, password);

        assertTrue(result);
    }

    @Test
    public void testRegisterDuplicateUsername() {
        // 先注册一个用户
        String email = "test2@example.com";
        String password = "Test12345!";
        User user = new User(email, User.hashPassword(password));
        BPlusTreeManagerUser.getTreeInstance(null).insert(email, user);

        // 尝试使用相同的用户名注册
        boolean result = loginCheckService.checkValid(email, password);

        assertFalse(result);
    }

    @Test
    public void testRegisterEmptyPassword() {
        String email = "test1@example.com";
        String password = "";

        boolean result = loginCheckService.checkValid(email, password);

        assertFalse(result);
    }

    @Test
    public void testRegisterInvalidPassword() {
        String email = "testtt1@example.com";
        String password = "invalidpassword";

        boolean result = loginCheckService.checkValid(email, password);

        assertFalse(result);
    }
}