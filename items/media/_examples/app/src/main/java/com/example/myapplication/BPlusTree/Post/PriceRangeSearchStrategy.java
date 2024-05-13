package com.example.myapplication.BPlusTree.Post;

import android.util.Log;

import com.example.myapplication.src.Post;
import com.example.myapplication.src.Tokenizer_Parser.Expression;
import com.example.myapplication.src.Tokenizer_Parser.Parser;
import com.example.myapplication.src.Tokenizer_Parser.Tokenizer;

public class PriceRangeSearchStrategy extends AbstractSearchStrategy {
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