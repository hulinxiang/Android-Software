package com.example.myapplication.src;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 这个类用于管理用户的信息。
 * <p>
 * 职责:
 * <p>
 * 存储用户信息，如用户名、邮箱、密码、联系信息等。
 * 可以包括用户的注册和登录方法，或者这部分功能由外部类处理。
 */

public class User {
    private String userId;
    private String email;  // Username 默认是
    private String passwordHash;
    private String name;
    private String address;
    private String phone;
    private PostList postList;  // 添加 PostList 属性
    private String userType;    // "0":Admin    "1":normal user
    private List<Post> posts;


    // 构造方法，只用邮箱和密码初始化
    public User(String email, String password) {
        // 因为Register只用邮箱和密码，所以用这两个信息初始化每个User实例
        this.userId = UUID.randomUUID().toString(); // 使用UUID生成唯一的用户ID
        this.email = email;
        this.passwordHash = password;//删掉了这里所有的hashPassword(password)方法，因为这里不需要哈希，否则会造成两次SHA256
        this.userType = "1";
    }

    // 全参数构造方法,但是userId是自动生成的
    public User(String email, String password, String name, String address, String phone) {
        this.userId = UUID.randomUUID().toString();  // 使用UUID生成唯一的用户ID
        this.email = email;
        this.passwordHash = password;  // 对密码进行哈希处理
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.userType = "1";
    }

    public User(String userId, String email, String password, String name, String address, String phone) {
        this.userId = userId;  // 生成唯一的用户ID
        this.email = email;
        this.passwordHash = password;  // 对密码进行哈希处理
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

//这个构造方法是用来从firebase直接导入的，userId不用自动生成，已经分配过了，等firebase数据改好后，要调用这个来初始化User表
    public User(String userId, String email, String password, String name, String address, String phone, String userType) {
        this.userId = userId;  // 生成唯一的用户ID
        this.email = email;
        this.passwordHash = password;  // 对密码进行哈希处理
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.userType = userType;
        this.posts = null;    // 先设为null，
    }

    // 使用SHA-256对密码进行哈希
    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to hash password", e);
        }
    }

    // 更新密码
    public void updatePassword(String newPassword) {
        this.passwordHash = hashPassword(newPassword);
    }

    // 更新姓名
    public void updateName(String newName) {
        this.name = newName;
    }

    // 更新地址
    public void updateAddress(String newAddress) {
        this.address = newAddress;
    }

    // 更新电话号码
    public void updatePhone(String newPhone) {
        this.phone = newPhone;
    }

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // 获取用户的 PostList
    public PostList getPostList() {
        return postList;
    }

    // 设置用户的 PostList
    public void setPostList(PostList postList) {
        this.postList = postList;
    }

}
