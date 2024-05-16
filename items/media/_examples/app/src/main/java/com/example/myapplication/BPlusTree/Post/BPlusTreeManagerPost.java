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
 * Manages the B+ tree for storing posts.
 * <p>
 * @author Linxiang Hu u7633783; Yichi Zhang
 */
public class BPlusTreeManagerPost {
    //The singleton instance of the B+ tree storing posts.
    private static BPlusTree<String, Post> postTree;

    /**
     * Retrieves the singleton instance of the B+ tree for posts.
     *
     * @param context The context of the application.
     * @return The singleton instance of the B+ tree.
     */
    public static synchronized BPlusTree<String, Post> getTreeInstance(Context context) {
        if (postTree == null) {
            postTree = new BPlusTree<>();
        }
        return postTree;
    }

    /**
     * Generates a list of randomly selected posts.
     *
     * @param context The context of the application.
     * @return A list of randomly selected posts.
     */
    public static List<Post> randomRecommender(Context context) {
        List<Post> allPosts = BPlusTreeManagerPost.getTreeInstance(context).queryAllData();
        Collections.shuffle(allPosts);
        List<Post> selectedPosts = new ArrayList<>();
        for (int i = 0; i < Math.min(8, allPosts.size()); i++) {
            selectedPosts.add(allPosts.get(i));
        }
        return selectedPosts;
    }

    /**
     * Searches for posts containing a specific keyword in their description or title.
     *
     * @param context The context of the application.
     * @param input   The keyword to search for.
     * @return A list of posts containing the keyword.
     */
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

    /**
     * Searches for a post with a specific ID.
     *
     * @param context The context of the application.
     * @param id      The ID of the post to search for.
     * @return The post with the specified ID, or null if not found.
     */
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

    /**
     * Executes a search strategy to filter posts based on specific criteria.
     *
     * @param context  The context of the application.
     * @param strategy The search strategy to use.
     * @param value    The value(s) to search for.
     * @return A list of posts matching the specified search criteria.
     */
    public static List<Post> searchByStrategy(Context context, SearchStrategy strategy, String... value) {
        return strategy.search(context, value);
    }

    /**
     * Searches for posts based on multiple conditions using various search strategies.
     *
     * @param context       The context of the application.
     * @param gender        The gender to filter posts by.
     * @param masterCategory The master category to filter posts by.
     * @param subCategory   The subcategory to filter posts by.
     * @param articleType   The article type to filter posts by.
     * @param baseColour    The base color to filter posts by.
     * @param season        The season to filter posts by.
     * @param usage         The usage to filter posts by.
     * @param minPrice      The minimum price to filter posts by.
     * @param maxPrice      The maximum price to filter posts by.
     * @return A list of posts matching the specified criteria.
     */
    public static List<Post> searchByMultipleConditions(Context context, String gender, String masterCategory, String subCategory, String articleType, String baseColour, String season, String usage, String minPrice, String maxPrice) {
        List<Post> resultPosts = getTreeInstance(context).queryAllData();

        // Filter posts by gender
        if (!TextUtils.isEmpty(gender)) {
            List<Post> genderPosts = searchByStrategy(context, new GenderSearchStrategy(), gender);
            resultPosts.retainAll(genderPosts);
        }

        // Filter posts by master category
        if (!TextUtils.isEmpty(masterCategory)) {
            List<Post> masterCategoryPosts = searchByStrategy(context, new MasterCategorySearchStrategy(), masterCategory);
            resultPosts.retainAll(masterCategoryPosts);
        }

        // Filter posts by subcategory
        if (!TextUtils.isEmpty(subCategory)) {
            List<Post> subCategoryPosts = searchByStrategy(context, new SubCategorySearchStrategy(), subCategory);
            resultPosts.retainAll(subCategoryPosts);
        }

        // Filter posts by article type
        if (!TextUtils.isEmpty(articleType)) {
            List<Post> articleTypePosts = searchByStrategy(context, new ArticleTypeSearchStrategy(), articleType);
            resultPosts.retainAll(articleTypePosts);
        }

        // Filter posts by base color
        if (!TextUtils.isEmpty(baseColour)) {
            List<Post> baseColourPosts = searchByStrategy(context, new SeasonSearchStrategy(), baseColour);
            resultPosts.retainAll(baseColourPosts);
        }

        // Filter posts by season
        if (!TextUtils.isEmpty(season)) {
            List<Post> seasonPosts = searchByStrategy(context, new SeasonSearchStrategy(), season);
            resultPosts.retainAll(seasonPosts);
        }

        // Filter posts by usage
        if (!TextUtils.isEmpty(usage)) {
            List<Post> usagePosts = searchByStrategy(context, new SeasonSearchStrategy(), usage);
            resultPosts.retainAll(usagePosts);
        }

        // Filter posts by price range
        if (!TextUtils.isEmpty(minPrice) && !TextUtils.isEmpty(maxPrice)) {
            List<Post> priceRangePosts = searchByStrategy(context, new PriceRangeSearchStrategy(), minPrice, maxPrice);
            resultPosts.retainAll(priceRangePosts);
        }

        return resultPosts;
    }



}
