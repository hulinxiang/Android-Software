package com.example.myapplication.src;

import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Linxiang Hu
 * A class helps to check if the username and password of a user when registering are valid
 */
public class SearchManager {

    // To check if it is a valid email
    private static final String EMAIL_PATTERN = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    //The valid password format must be a combination of lowercase letters, uppercase letters and special symbols.
    // and there cannot be spaces between it. The length of it should between 8 and 20.
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[_@!?#$%^&+=])(?=\\S+$).{8,20}$";


    /**
     * @param username the username string to check if it is valid
     * @return true, if it is valid; false, if it is not
     */
    public static boolean validateUsername(String username) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
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
}
