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

    public void addPost(Post post) {
        posts.add(post);
    }

    public void removePost(Post post) {
        posts.remove(post);
    }

    public List<Post> getPosts() {
        return posts;
    }

    public List<Post> searchPosts(String keyword) {
        List<Post> result = new ArrayList<>();
        for (Post post : posts) {
            if (post.getTopic().contains(keyword) || post.getDescription().contains(keyword)) {
                result.add(post);
            }
        }
        return result;
    }
}
