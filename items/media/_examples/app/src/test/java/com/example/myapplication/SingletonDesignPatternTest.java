package com.example.myapplication;

import com.example.myapplication.src.Firebase.PostManager.FirebasePostHelper;
import com.example.myapplication.src.Firebase.PostManager.FirebasePostManager;
import com.example.myapplication.src.Firebase.UserManager.FirebaseUserHelper;
import com.example.myapplication.src.Firebase.UserManager.FirebaseUserManager;

import org.junit.Test;
import org.junit.Assert;

/**
 * This class is used to test the Singleton Design Pattern implementation in the FirebaseUserManager and FirebasePostManager classes.
 * It tests the creation of instances of FirebaseUserHelper and FirebasePostHelper using the getInstance method of their respective managers.
 * It verifies that the instances created by the getInstance method are the same, thus confirming the Singleton Design Pattern.
 *
 * @Author Yichi Zhang
 */
public class SingletonDesignPatternTest {

    /**
     * This test case tests the Singleton Design Pattern implementation in the FirebaseUserManager class.
     * It creates two instances of FirebaseUserHelper using the getInstance method of FirebaseUserManager.
     * It verifies that the two instances are the same, thus confirming the Singleton Design Pattern.
     */
    @Test
    public void testSingletonUser() {
        FirebaseUserHelper instance1 = FirebaseUserManager.getInstance(null);
        FirebaseUserHelper instance2 = FirebaseUserManager.getInstance(null);

        Assert.assertSame(instance1, instance2);
    }

    /**
     * This test case tests the Singleton Design Pattern implementation in the FirebasePostManager class.
     * It creates two instances of FirebasePostHelper using the getInstance method of FirebasePostManager.
     * It verifies that the two instances are the same, thus confirming the Singleton Design Pattern.
     */
    @Test
    public void testSingletonPost() {
        FirebasePostHelper instance1 = FirebasePostManager.getInstance(null);
        FirebasePostHelper instance2 = FirebasePostManager.getInstance(null);

        Assert.assertSame(instance1, instance2);
    }
}
