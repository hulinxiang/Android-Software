package com.example.myapplication.BPlusTree.Post;

import android.util.Log;

import com.example.myapplication.src.Post;
import com.example.myapplication.src.Tokenizer_Parser.Parser;
import com.example.myapplication.src.Tokenizer_Parser.Tokenizer;

/**
 * The PriceRangeSearchStrategy class extends the AbstractSearchStrategy class.
 * It provides a specific implementation of the matchCriteria method that checks if a post's price is within a specified range.
 *
 * @see AbstractSearchStrategy
 * @see Post
 * @see Tokenizer
 * @see Parser
 * @author Yichi Zhang u7748799
 */
public class PriceRangeSearchStrategy extends AbstractSearchStrategy {

    /**
     * Checks if a post's price is within the specified range.
     * This method throws an IllegalArgumentException if the number of values is not equal to 2.
     *
     * @param post the post to check
     * @param values the values to match against, expected to be minPrice and maxPrice
     * @return true if the post's price is within the specified range, false otherwise
     */
    @Override
    protected boolean matchCriteria(Post post, String... values) {
        if (values.length != 2) {
            throw new IllegalArgumentException("Expected 2 arguments: minPrice and maxPrice");
        }

        String minPriceStr = values[0];
        String maxPriceStr = values[1];

        double minPrice = parsePrice(minPriceStr);
        double maxPrice = parsePrice(maxPriceStr);
        double postPrice = post.getPrice();

        Log.d("PriceRangeSearchStrategy", "minPrice: " + minPrice + ", maxPrice: " + maxPrice + ", postPrice: " + postPrice);
        Log.d("PriceRangeSearchStrategy", "minPrice <= postPrice <= maxPrice: " + (minPrice <= postPrice && postPrice <= maxPrice));

        return (postPrice >= minPrice && postPrice <= maxPrice);
    }

    /**
     * Parses a price from a string.
     * This method uses a Tokenizer and a Parser to parse the price.
     *
     * @param priceStr the string to parse
     * @return the parsed price
     */
    private double parsePrice(String priceStr) {
        Tokenizer tokenizer = new Tokenizer(priceStr);
        Parser parser = new Parser(tokenizer);
        String parseRes = parser.parse().show();
        String[] parts = parseRes.split("\\|");
        StringBuilder stringBuilder = new StringBuilder();
        for (String part : parts) {
            stringBuilder.append(part);
        }
        return Double.parseDouble(stringBuilder.toString());
    }
}