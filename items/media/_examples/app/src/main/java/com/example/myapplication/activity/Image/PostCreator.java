package com.example.myapplication.activity.Image;
import android.content.Context;
import com.example.myapplication.BPlusTree.Post.BPlusTreeManagerPost;
import com.example.myapplication.src.Firebase.PostManager.FirebasePostManager;
import com.example.myapplication.src.Post;
import com.example.myapplication.src.SessionManager;
import com.example.myapplication.src.User;

/**
 *
 * The PostCreator class handles the creation of new posts.
 * It creates a post object with the provided details, associates it with the current user,
 * and saves it both locally using a BPlus Tree and remotely in Firebase.
 * @author Yingxuan Tang
 */
public class PostCreator {
    private Context context;

    /**
     * Constructor for PostCreator.
     *
     * @param context  The context in which the PostCreator is used.
     */
    public PostCreator(Context context) {
        this.context = context;
    }



    /**
     * Creates a new post with the given details.
     *
     * @param userID  The ID of the user creating the post.
     * @param gender  The gender category of the post.
     * @param masterCategoryName  The master category name of the post.
     * @param subCategoryName  The subcategory name of the post.
     * @param articleTypeName  The article type name of the post.
     * @param baseColour  The base colour of the post.
     * @param season  The season category of the post.
     * @param year  The year associated with the post.
     * @param usage  The usage category of the post.
     * @param productDisplayName  The display name of the product.
     * @param productPrice  The price of the product.
     * @param productStatus  The status of the product.
     * @param imageUrl  The URL of the product image.
     * @param productDescription  The description of the product.
     * @param commentText  The comment text associated with the post.
     *
     * Method:
     * - Creates a new post object with the provided details.
     * - Retrieves the current user's email from the session manager and uses it as the userID for the post.
     * - Associates the post with the current user by adding it to the user's list of owned posts.
     * - Saves the post locally in a BPlus Tree data structure.
     * - Saves the post remotely in Firebase.
     */
    public void createPost(String userID, String gender, String masterCategoryName, String subCategoryName,
                           String articleTypeName, String baseColour, String season, int year, String usage,
                           String productDisplayName, double productPrice, String productStatus, String imageUrl,
                           String productDescription, String commentText) {
        // Create a new post
//        Post newPost = new Post(userID, gender, masterCategoryName, subCategoryName, articleTypeName,
//                baseColour, season, year, usage, productDisplayName, productPrice, productStatus, imageUrl,
//                productDescription, commentText);

        // Get the current user's email
        User currentUser = SessionManager.getInstance().getUser();
        String userEmail = currentUser != null ? currentUser.getEmail() : "";

        // Create a new post using the user's email as the userID
        Post newPost = new Post(userEmail, gender, masterCategoryName, subCategoryName, articleTypeName,
                baseColour, season, year, usage, productDisplayName, productPrice, productStatus,
                imageUrl, productDescription, commentText);

        // Associate the post with the current user
//        User currentUser = SessionManager.getInstance().getUser();
        if (currentUser != null) {
            currentUser.addOwnPost(newPost);
        }

        // Save the post to the BPlus Tree
        BPlusTreeManagerPost.getTreeInstance(context).insert(newPost.getPostID(), newPost);

        // Save the post to Firebase
        FirebasePostManager.getInstance(context).addPost(newPost);
    }
}
