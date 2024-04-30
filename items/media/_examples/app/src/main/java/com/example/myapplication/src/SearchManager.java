package com.example.myapplication.src;

import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 负责商品的搜索逻辑。
 * <p>
 * 职责:
 * <p>
 * 提供搜索功能，允许用户根据关键词、价格范围等条件搜索商品。
 * 可能与ProductList类交互来获取过滤后的商品数据。
 */
public class SearchManager {

    // The valid username format can contain any letters, numbers and "_@#^?!&+=". The length of username should be
    // in the range between 6 and 16.
    private static final String USERNAME_PATTERN = "^[a-zA-Z0-9_@#^?!&+=]{6,16}$";
    //The valid password format must be a combination of lowercase letters, uppercase letters and special symbols.
    // and there cannot be spaces between it. The length of it should between 8 and 20.
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[_@!?#$%^&+=])(?=\\S+$).{8,20}$";
    // A valid search query can contain letters, numbers, '.','-','?','!'
    private static final String SEARCH_PATTERN = "^[a-zA-Z0-9 ,.-?!]+$";


    /**
     * @param username the username string to check if it is valid
     * @return true, if it is valid; false, if it is not
     */
    public static boolean validateUsername(String username) {
        Pattern pattern = Pattern.compile(USERNAME_PATTERN);
        Matcher matcher = pattern.matcher(username);
        if (matcher.matches()){
            return true;
        }
        Log.d("Username====","username is invalid");
        return false;
    }

    /**
     * @param password the password string to check if it is valid
     * @return true, if it is valid; false, if it is not
     */
    public static boolean validatePassword(String password) {
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        if (matcher.matches()){
            return true;
        }
        Log.d("Password====","password is invalid");
        return false;
    }

    /**
     * @param searchQuery the search (products or posts) query to validate
     * @return true, if it is valid; false, if it is not
     */
    public static boolean validateSearchQuery(String searchQuery) {
        Pattern pattern = Pattern.compile(SEARCH_PATTERN);
        Matcher matcher = pattern.matcher(searchQuery);
        return matcher.matches();
    }

}
