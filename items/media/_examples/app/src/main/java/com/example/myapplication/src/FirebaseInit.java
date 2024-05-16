package com.example.myapplication.src;

import com.example.myapplication.BPlusTree.Post.BPlusTreeManagerPost;
import com.example.myapplication.BPlusTree.Remark.BPlusTreeManagerRemark;
import com.example.myapplication.BPlusTree.User.BPlusTreeManagerUser;
import com.example.myapplication.src.Remark.AnonymousRemarkFactoryManager;
import com.example.myapplication.src.Remark.CommonRemarkFactoryManager;
import com.example.myapplication.src.Remark.RemarkDemo;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.app.Application;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Yingxuan Tang, Linxiang Hu, Yichi Zhang u7748799
 *
 * The FirebaseInit class initializes Firebase and loads data from Firebase into the B+ tree data structures.
 */
public class FirebaseInit extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize Firebase
        FirebaseApp.initializeApp(this);
        loadDataFromFirebase();
    }

    /**
     * Loads data from Firebase into the B+ tree data structures.
     */
    private void loadDataFromFirebase() {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        // Loading Post data
        DatabaseReference postsRef = database.child("post");
        // Loading User data
        DatabaseReference usersRef = database.child("user");

        DatabaseReference remarkRef = database.child("remark");

        // Initialize the User data first, so that you can initialize the ownPosts property under
        // the user to indicate how many posts that user has made

        //''addListenerForSingleValueEvent'' will execute the onDataChange method   immediately and
        // after executing that method once, it stops listening to the reference location it is attached to.
        usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Parse each field
                    String userId = snapshot.child("userID").getValue(String.class);
                    String email = snapshot.child("email").getValue(String.class);
                    String password = snapshot.child("password").getValue(String.class);  // Make sure this is the original code
                    String name = snapshot.child("name").getValue(String.class);
                    String address = snapshot.child("address").getValue(String.class);
                    String phone = snapshot.child("phone").getValue(String.class);
                    String userType = snapshot.child("userType").getValue(String.class);
                    String userIndexInFirebase = snapshot.child("userIndexInFirebase").getValue(String.class);

                    // Construct creates the User object
                    if (email != null && password != null && userId != null) {
                        User user = new User(userId, email, password, name, address, phone, userType, userIndexInFirebase);
                        BPlusTreeManagerUser.getTreeInstance(FirebaseInit.this).insert(email, user);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("FirebaseInit", "Error loading data: " + databaseError.getMessage());
                System.err.println("Failed to load user data: " + databaseError.getMessage());
            }
        });


        postsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Parse each field of post
                    String userID = snapshot.child("UserID").getValue(String.class);
                    String postID = snapshot.child("postID").getValue(String.class);
                    String gender = snapshot.child("gender").getValue(String.class);
                    String masterCategory = snapshot.child("masterCategory").getValue(String.class);
                    String subCategory = snapshot.child("subCategory").getValue(String.class);
                    String articleType = snapshot.child("articleType").getValue(String.class);
                    String baseColour = snapshot.child("baseColour").getValue(String.class);
                    String season = snapshot.child("season").getValue(String.class);
                    Object yearObj = snapshot.child("year").getValue();
                    String year;
                    if (yearObj instanceof Long) {
                        year = String.valueOf(yearObj);
                    } else if (yearObj instanceof String) {
                        year = (String) yearObj;
                    } else {
                        // Handle cases where yearObj is null or other types
                        year = "0";
                    }
                    String usage = snapshot.child("usage").getValue(String.class);
                    String productDisplayName = snapshot.child("productDisplayName").getValue(String.class);
                    String price = snapshot.child("price").getValue(String.class);
                    String status = snapshot.child("status").getValue(String.class);
                    String imageUrl = snapshot.child("image_url").getValue(String.class);
                    String description = snapshot.child("description").getValue(String.class);
                    String comments = snapshot.child("comments").getValue(String.class);

                    String postIndexInFirebase = snapshot.child("postIndexInFirebase").getValue(String.class);
                    String likeIDs = snapshot.child("likeIDs").getValue(String.class);
                    String buyIDs = snapshot.child("buyIDs").getValue(String.class);

                    assert year != null;
                    assert price != null;

                    // Create a Post object
                    Post post = new Post(postID, userID, gender, masterCategory, subCategory, articleType, baseColour, season, Integer.parseInt(year), usage, productDisplayName, Double.parseDouble(price), status, imageUrl, description, comments, postIndexInFirebase, likeIDs, buyIDs);

                    BPlusTreeManagerPost.getTreeInstance(FirebaseInit.this).insert(postID, post);
                    User author = BPlusTreeManagerUser.getTreeInstance(FirebaseInit.this).query(userID).get(0);
                    author.updateOwns(post);

                    if (!TextUtils.isEmpty(likeIDs)) {
                        Log.d("Constructing usersLike list........", "Adding user: " + likeIDs + " to the local tree");
                        String[] likeUsersId = likeIDs.split(",");
                        for (String uid : likeUsersId) {
                            try {
                                List<User> userList = BPlusTreeManagerUser.getTreeInstance(FirebaseInit.this).query(uid);
                                if (!userList.isEmpty()) {
                                    User user = userList.get(0);
                                    user.updateLikes(post);
                                    Log.d("Constructing usersLike list........", "Adding user: " + uid + " to the local tree");
                                } else {
                                    Log.w("FirebaseInit", "User not found: " + uid);
                                }
                            } catch (Exception e) {
                                Log.e("FirebaseInit", "Error updating user likes: " + e.getMessage());
                            }
                        }
                    }

                    if (!TextUtils.isEmpty(buyIDs)) {
                        Log.d("Constructing usersBuy list........", "Adding post: " + postID + " to the users' buyPost list");
                        String[] buyUsersId = buyIDs.split(",");
                        for (String uid : buyUsersId) {
                            try {
                                List<User> userList = BPlusTreeManagerUser.getTreeInstance(FirebaseInit.this).query(uid);
                                if (!userList.isEmpty()) {
                                    User user = userList.get(0);
                                    user.updateBuys(post);
                                    Log.d("Constructing usersBuy list........", "Adding post: " + postID + " to user: " + uid + "'s buyPost list");
                                } else {
                                    Log.w("FirebaseInit", "User not found: " + uid);
                                }
                            } catch (Exception e) {
                                Log.e("FirebaseInit", "Error updating user buys: " + e.getMessage());
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("FirebaseInit", "Failed to load post data: " + databaseError.getMessage());
            }
        });

        remarkRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot shot : snapshot.getChildren()) {
                    String index = shot.child("Index").getValue(String.class);
                    String postID = shot.child("PostID").getValue(String.class);
                    String remark = shot.child("Remark").getValue(String.class);
                    String userEmail = shot.child("UserEmail").getValue(String.class);
                    RemarkDemo remarkDemo = null;
                    if ("Anonymous User".equals(userEmail)) {
                        remarkDemo = AnonymousRemarkFactoryManager.getInstance().createWithIndex(remark, userEmail, postID, index);
                    } else {
                        remarkDemo = CommonRemarkFactoryManager.getInstance().createWithIndex(remark, userEmail, postID, index);
                    }
                    List<List<RemarkDemo>> list = BPlusTreeManagerRemark.getTreeInstance().query(postID);
                    if (list.isEmpty()) {
                        List<RemarkDemo> value = new ArrayList<>();
                        value.add(remarkDemo);
                        BPlusTreeManagerRemark.getTreeInstance().insert(postID, value);
                    } else {
                        list.get(0).add(remarkDemo);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("FirebaseInit", "Failed to load remark data: " + error.getMessage());
            }
        });
    }
}