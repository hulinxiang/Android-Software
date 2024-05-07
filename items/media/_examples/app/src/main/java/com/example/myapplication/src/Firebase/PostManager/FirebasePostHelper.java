package com.example.myapplication.src.Firebase.PostManager;


import com.example.myapplication.src.Post;
import com.example.myapplication.src.Tag;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class FirebasePostHelper {
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("Post");


//    private void addPost(Post post) {
//
//        DatabaseReference newPostRef = myRef.push();
//
//        newPostRef.child("userID").setValue(post.getPostID());
//        newPostRef.child("postID").setValue(post.getPostID());
//        newPostRef.child("gender").setValue(post.getTag().getGender());
//        newPostRef.child("masterCategory").setValue(post.getTag().getMasterCategory());
//        String subCategory = newPostRef.child("subCategory").setValue(post.getTag().);
//        String articleType = snapshot.child("articleType").getValue(String.class);
//        String baseColour = snapshot.child("baseColour").getValue(String.class);
//        String season = snapshot.child("season").getValue(String.class);
//        String year = snapshot.child("year").getValue(String.class);
//        String usage = snapshot.child("usage").getValue(String.class);
//        String productDisplayName = snapshot.child("productDisplayName").getValue(String.class);
//        String price = snapshot.child("price").getValue(String.class);
//        String status = snapshot.child("status").getValue(String.class);
//        String imageUrl = snapshot.child("image_url").getValue(String.class);
//        String description = snapshot.child("description").getValue(String.class);
//        String comments = snapshot.child("comment").getValue(String.class);
//
//        assert year != null;
//        assert price != null;
//
//        // 创建Tag对象
//        Tag.ArticleType articleTypeObj = new Tag.ArticleType(articleType);
//        Tag.SubCategory subCategoryObj = new Tag.SubCategory(subCategory, articleTypeObj);
//        Tag.MasterCategory masterCategoryObj = new Tag.MasterCategory(masterCategory, subCategoryObj);
//        Tag tag = new Tag(gender, masterCategoryObj, baseColour, season, Integer.parseInt(year), usage);
//
//
//    }


}
