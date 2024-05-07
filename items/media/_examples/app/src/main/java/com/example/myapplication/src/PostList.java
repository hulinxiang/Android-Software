package com.example.myapplication.src;

import java.util.ArrayList;
import java.util.List;

/**
 * 这个类管理一个商品集合，用于搜索和展示商品列表。
 *
 * 职责:
 *
 * 存储一个post列表。
 * 提供添加商品、删除商品和获取商品列表的方法。
 * 可以包含搜索商品的方法。
 */
public class PostList {
    private List<Post> posts = new ArrayList<>();

    // 添加Post到列表
    public void addPost(Post post) {
        posts.add(post);
    }

    // 从列表中删除Post
    public void removePost(Post post) {
        posts.remove(post);
    }

    // 获取Post列表
    public List<Post> getPosts() {
        return posts;
    }

    // 根据关键字搜索Post，这里使用产品显示名称和描述作为搜索依据
    public List<Post> searchPosts(String keyword) {
        List<Post> result = new ArrayList<>();
        for (Post post : posts) {
            if (post.getProductDisplayName().contains(keyword) || post.getDescription().contains(keyword)) {
                result.add(post);
            }
        }
        return result;
    }

    // Check if the post list is empty
    public boolean isEmpty() {
        return posts.isEmpty();
    }

    // Retrieve a specific post by index
    public Post get(int index) {
        return posts.get(index);
    }
}
