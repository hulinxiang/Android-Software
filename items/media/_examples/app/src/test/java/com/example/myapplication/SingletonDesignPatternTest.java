package com.example.myapplication;

import com.example.myapplication.src.Firebase.PostManager.FirebasePostHelper;
import com.example.myapplication.src.Firebase.PostManager.FirebasePostManager;
import com.example.myapplication.src.Firebase.UserManager.FirebaseUserHelper;
import com.example.myapplication.src.Firebase.UserManager.FirebaseUserManager;

import org.junit.Test;
import org.junit.Assert;

public class SingletonDesignPatternTest {
    @Test
    public void testSingletonUser() {
        FirebaseUserHelper instance1 = FirebaseUserManager.getInstance(null);
        FirebaseUserHelper instance2 = FirebaseUserManager.getInstance(null);

//        Assertions.assertSame(instance1, instance2);
        Assert.assertSame(instance1, instance2);
    }

    @Test
    public void testSingletonPost() {
        FirebasePostHelper instance1 = FirebasePostManager.getInstance(null);
        FirebasePostHelper instance2 = FirebasePostManager.getInstance(null);

//        Assertions.assertSame(instance1, instance2);
        Assert.assertSame(instance1, instance2);
    }
}
