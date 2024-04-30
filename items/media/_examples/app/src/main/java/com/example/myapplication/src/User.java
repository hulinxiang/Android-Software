package com.example.myapplication.src;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * 这个类用于管理用户的信息。
 *
 * 职责:
 *
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

    public User(String email, String password) {
        // 因为Register只用邮箱和密码，所以用这两个信息初始化每个User实例
        this.userId = UUID.randomUUID().toString();  // 用于生成一个唯一的标识符（UUID）并将其转换为字符串形式，用作用户的ID
        this.email = email;
        this.passwordHash = hashPassword(password);
    }

    // 使用SHA-256对密码进行哈希
    private String hashPassword(String password) {
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


}

