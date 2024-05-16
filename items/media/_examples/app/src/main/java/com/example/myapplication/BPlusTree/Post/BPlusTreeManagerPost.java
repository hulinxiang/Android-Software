package com.example.myapplication.BPlusTree.Post;

import android.content.Context;
import android.text.TextUtils;

import com.example.myapplication.BPlusTree.BPlusTree;
import com.example.myapplication.BPlusTree.Post.Tag.ArticleTypeSearchStrategy;
import com.example.myapplication.BPlusTree.Post.Tag.GenderSearchStrategy;
import com.example.myapplication.BPlusTree.Post.Tag.MasterCategorySearchStrategy;
import com.example.myapplication.BPlusTree.Post.Tag.SeasonSearchStrategy;
import com.example.myapplication.BPlusTree.Post.Tag.SubCategorySearchStrategy;
import com.example.myapplication.src.Post;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Yichi Zhang
 */
public class BPlusTreeManagerPost {
    private static BPlusTree<String, Post> postTree;

    public static synchronized BPlusTree<String, Post> getTreeInstance(Context context) {
        if (postTree == null) {
            postTree = new BPlusTree<>();
        }
        return postTree;
    }


    public static List<Post> randomRecommender(Context context) {
        List<Post> allPosts = BPlusTreeManagerPost.getTreeInstance(context).queryAllData();
        Collections.shuffle(allPosts);
        List<Post> selectedPosts = new ArrayList<>();
        for (int i = 0; i < Math.min(8, allPosts.size()); i++) {
            selectedPosts.add(allPosts.get(i));
        }
        return selectedPosts;
    }

    public static List<Post> searchKeyword(Context context, String input) {
        List<Post> allPosts = BPlusTreeManagerPost.getTreeInstance(context).queryAllData();
        List<Post> res = new ArrayList<>();
        for (Post post : allPosts) {
            String description = post.getDescription();
            String title = post.getProductDisplayName();
            if (description.trim().toLowerCase().contains(input.trim().toLowerCase())
                    || input.trim().toLowerCase().contains(title.trim().toLowerCase())) {
                res.add(post);
            }
        }
        return res;
    }

    public static Post searchPostId(Context context, String id) {
        List<Post> allPosts = BPlusTreeManagerPost.getTreeInstance(context).queryAllData();
        for (Post post : allPosts) {
            String postId = post.getPostID();
            if (postId.equals(id)) {
                return post;
            }
        }
        return null;
    }

    public static List<Post> searchByStrategy(Context context, SearchStrategy strategy, String... value) {
        return strategy.search(context, value);
    }

    public static List<Post> searchByMultipleConditions(Context context, String gender, String masterCategory, String subCategory, String articleType, String baseColour, String season, String usage, String minPrice, String maxPrice) {
        List<Post> resultPosts = getTreeInstance(context).queryAllData();

        // 根据性别进行搜索
        if (!TextUtils.isEmpty(gender)) {
            List<Post> genderPosts = searchByStrategy(context, new GenderSearchStrategy(), gender);
            resultPosts.retainAll(genderPosts);
        }

        // 根据主分类进行搜索
        if (!TextUtils.isEmpty(masterCategory)) {
            List<Post> masterCategoryPosts = searchByStrategy(context, new MasterCategorySearchStrategy(), masterCategory);
            resultPosts.retainAll(masterCategoryPosts);
        }

        // 根据子分类进行搜索
        if (!TextUtils.isEmpty(subCategory)) {
            List<Post> subCategoryPosts = searchByStrategy(context, new SubCategorySearchStrategy(), subCategory);
            resultPosts.retainAll(subCategoryPosts);
        }

        // 根据款式进行搜索
        if (!TextUtils.isEmpty(articleType)) {
            List<Post> articleTypePosts = searchByStrategy(context, new ArticleTypeSearchStrategy(), articleType);
            resultPosts.retainAll(articleTypePosts);
        }

        // 根据颜色进行搜索
        if (!TextUtils.isEmpty(baseColour)) {
            List<Post> baseColourPosts = searchByStrategy(context, new SeasonSearchStrategy(), baseColour);
            resultPosts.retainAll(baseColourPosts);
        }

        // 根据季节进行搜索
        if (!TextUtils.isEmpty(season)) {
            List<Post> seasonPosts = searchByStrategy(context, new SeasonSearchStrategy(), season);
            resultPosts.retainAll(seasonPosts);
        }

        // 根据用途进行搜索
        if (!TextUtils.isEmpty(usage)) {
            List<Post> usagePosts = searchByStrategy(context, new SeasonSearchStrategy(), usage);
            resultPosts.retainAll(usagePosts);
        }

        // 根据价格区间进行搜索
        if (!TextUtils.isEmpty(minPrice) && !TextUtils.isEmpty(maxPrice)) {
            List<Post> priceRangePosts = searchByStrategy(context, new PriceRangeSearchStrategy(), minPrice, maxPrice);
            resultPosts.retainAll(priceRangePosts);
        }

        return resultPosts;
    }



}
