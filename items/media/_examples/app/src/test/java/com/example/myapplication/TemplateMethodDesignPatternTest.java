package com.example.myapplication;


import com.example.myapplication.BPlusTree.Post.AbstractSearchStrategy;
import com.example.myapplication.BPlusTree.Post.Tag.*;
import com.example.myapplication.src.Post;
import com.example.myapplication.src.Tag;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TemplateMethodDesignPatternTest {

    @Test
    public void testSearchStrategies() throws Exception {
        // Create a sample post with a tag
        Tag tag = new Tag("T-shirt", "Apparel", "Tops", "Male", "Summer", "Casual", 2000,"Unknown");
        Post post = new Post(
                "user123", "Male", "Apparel", "Tops", "T-shirt",
                "Blue", "Summer", 2023, "Casual", "Adidas Originals Men's Trefoil T-Shirt",
                29.99, "Available", "https://example.com/image1.jpg", "Comfortable and stylish t-shirt.", "Nice shirt!"
        );
        post.setTag(tag);

        // Test ArticleTypeSearchStrategy
        ArticleTypeSearchStrategy articleTypeSearchStrategy = new ArticleTypeSearchStrategy();
        Method articleTypeMethod = AbstractSearchStrategy.class.getDeclaredMethod("matchCriteria", Post.class, String[].class);
        articleTypeMethod.setAccessible(true);
        assertTrue((boolean) articleTypeMethod.invoke(articleTypeSearchStrategy, post, "T-shirt"));
        assertFalse((boolean) articleTypeMethod.invoke(articleTypeSearchStrategy, post, "Dress"));

        // Test BaseColorSearchStrategy
        BaseColorSearchStrategy baseColorSearchStrategy = new BaseColorSearchStrategy();
        Method baseColorMethod = AbstractSearchStrategy.class.getDeclaredMethod("matchCriteria", Post.class, String[].class);
        baseColorMethod.setAccessible(true);
        assertTrue((boolean) baseColorMethod.invoke(baseColorSearchStrategy, post, "Red"));
        assertFalse((boolean) baseColorMethod.invoke(baseColorSearchStrategy, post, "Blue"));

        // Test GenderSearchStrategy
        GenderSearchStrategy genderSearchStrategy = new GenderSearchStrategy();
        Method genderMethod = AbstractSearchStrategy.class.getDeclaredMethod("matchCriteria", Post.class, String[].class);
        genderMethod.setAccessible(true);
        assertTrue((boolean) genderMethod.invoke(genderSearchStrategy, post, "Male"));
        assertFalse((boolean) genderMethod.invoke(genderSearchStrategy, post, "Female"));

        // Test MasterCategorySearchStrategy
        MasterCategorySearchStrategy masterCategorySearchStrategy = new MasterCategorySearchStrategy();
        Method masterCategoryMethod = AbstractSearchStrategy.class.getDeclaredMethod("matchCriteria", Post.class, String[].class);
        masterCategoryMethod.setAccessible(true);
        assertTrue((boolean) masterCategoryMethod.invoke(masterCategorySearchStrategy, post, "Apparel"));
        assertFalse((boolean) masterCategoryMethod.invoke(masterCategorySearchStrategy, post, "Footwear"));

        // Test SeasonSearchStrategy
        SeasonSearchStrategy seasonSearchStrategy = new SeasonSearchStrategy();
        Method seasonMethod = AbstractSearchStrategy.class.getDeclaredMethod("matchCriteria", Post.class, String[].class);
        seasonMethod.setAccessible(true);
        assertTrue((boolean) seasonMethod.invoke(seasonSearchStrategy, post, "Summer"));
        assertFalse((boolean) seasonMethod.invoke(seasonSearchStrategy, post, "Winter"));

        // Test SubCategorySearchStrategy
        SubCategorySearchStrategy subCategorySearchStrategy = new SubCategorySearchStrategy();
        Method subCategoryMethod = AbstractSearchStrategy.class.getDeclaredMethod("matchCriteria", Post.class, String[].class);
        subCategoryMethod.setAccessible(true);
        assertTrue((boolean) subCategoryMethod.invoke(subCategorySearchStrategy, post, "Tops"));
        assertFalse((boolean) subCategoryMethod.invoke(subCategorySearchStrategy, post, "Bottoms"));

        // Test UsageSearchStrategy
        UsageSearchStrategy usageSearchStrategy = new UsageSearchStrategy();
        Method usageMethod = AbstractSearchStrategy.class.getDeclaredMethod("matchCriteria", Post.class, String[].class);
        usageMethod.setAccessible(true);
        assertTrue((boolean) usageMethod.invoke(usageSearchStrategy, post, "Casual"));
        assertFalse((boolean) usageMethod.invoke(usageSearchStrategy, post, "Formal"));
    }
}