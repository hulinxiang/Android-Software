package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.BPlusTree.Post.BPlusTreeManagerPost;
import com.example.myapplication.R;
import com.example.myapplication.src.Post;
import com.google.gson.Gson;

import java.util.List;

public class TagSelectionActivity extends AppCompatActivity {

    private Spinner spinnerGender, spinnerMasterCategory, spinnerSubCategory, spinnerArticleType, spinnerBaseColor, spinnerSeason, spinnerUsage;
    private EditText editTextMinPrice, editTextMaxPrice;
    private Button buttonSaveInput, buttonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag_selection);

        // Initialize views
        spinnerGender = findViewById(R.id.spinner_gender);
        spinnerMasterCategory = findViewById(R.id.spinner_master_category);
        spinnerSubCategory = findViewById(R.id.spinner_sub_category);
        spinnerArticleType = findViewById(R.id.spinner_article_type);
        spinnerBaseColor = findViewById(R.id.spinner_base_color);
        spinnerSeason = findViewById(R.id.spinner_season);
        spinnerUsage = findViewById(R.id.spinner_usage);
        editTextMinPrice = findViewById(R.id.edit_text_min_price);
        editTextMaxPrice = findViewById(R.id.edit_text_max_price);
        buttonSaveInput = findViewById(R.id.button_search);
        buttonCancel = findViewById(R.id.button_return);


        // Set up spinners with data
        setupSpinners();

        // Handle search button click
        buttonSaveInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gender = getSelectedSpinnerValue(spinnerGender);
                String masterCategory = getSelectedSpinnerValue(spinnerMasterCategory);
                String subCategory = getSelectedSpinnerValue(spinnerSubCategory);
                String articleType = getSelectedSpinnerValue(spinnerArticleType);
                String baseColor = getSelectedSpinnerValue(spinnerBaseColor);
                String season = getSelectedSpinnerValue(spinnerSeason);
                String usage = getSelectedSpinnerValue(spinnerUsage);
                String minPrice = editTextMinPrice.getText().toString();
                String maxPrice = editTextMaxPrice.getText().toString();


                if (minPrice.isEmpty()) {
                    minPrice = "0";
                }
                if (maxPrice.isEmpty()) {
                    maxPrice = "500";
                }

                Intent intent = new Intent(TagSelectionActivity.this, SearchActivity.class);
                intent.putExtra("gender",gender);
                intent.putExtra("masterCategory",masterCategory);
                intent.putExtra("subCategory",subCategory);
                intent.putExtra("articleType",articleType);
                intent.putExtra("baseColor",baseColor);
                intent.putExtra("season", season);
                intent.putExtra("usage", usage);
                intent.putExtra("minPrice", minPrice);
                intent.putExtra("maxPrice", maxPrice);
                startActivity(intent);

            }
        });

        // Handle cancel button click
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the activity and return to the previous screen
                finish();
            }
        });

    }
    private void setupSpinners() {
        // Set up gender spinner
        String[] genderCategories = getResources().getStringArray(R.array.gender_categories);
        CustomSpinnerAdapter adapterGender = new CustomSpinnerAdapter(this, android.R.layout.simple_spinner_item, genderCategories);
        adapterGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(adapterGender);

        // Set up other spinners similarly...
        String[] masterCategoryCategories = getResources().getStringArray(R.array.master_category_categories);
        CustomSpinnerAdapter adapterMasterCategory = new CustomSpinnerAdapter(this, android.R.layout.simple_spinner_item, masterCategoryCategories);
        adapterMasterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMasterCategory.setAdapter(adapterMasterCategory);

        String[] subCategoryCategories = getResources().getStringArray(R.array.sub_category_categories);
        CustomSpinnerAdapter adapterSubCategory = new CustomSpinnerAdapter(this, android.R.layout.simple_spinner_item, subCategoryCategories);
        adapterSubCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSubCategory.setAdapter(adapterSubCategory);

        String[] articleTypeCategories = getResources().getStringArray(R.array.article_type_categories);
        CustomSpinnerAdapter adapterArticleType = new CustomSpinnerAdapter(this, android.R.layout.simple_spinner_item, articleTypeCategories);
        adapterArticleType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerArticleType.setAdapter(adapterArticleType);

        String[] baseColorTypes = getResources().getStringArray(R.array.base_color_types);
        CustomSpinnerAdapter adapterBaseColor = new CustomSpinnerAdapter(this, android.R.layout.simple_spinner_item, baseColorTypes);
        adapterBaseColor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBaseColor.setAdapter(adapterBaseColor);

        String[] seasonTypes = getResources().getStringArray(R.array.season_types);
        CustomSpinnerAdapter adapterSeason = new CustomSpinnerAdapter(this, android.R.layout.simple_spinner_item, seasonTypes);
        adapterSeason.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSeason.setAdapter(adapterSeason);

        String[] usageTypes = getResources().getStringArray(R.array.usage_types);
        CustomSpinnerAdapter adapterUsage = new CustomSpinnerAdapter(this, android.R.layout.simple_spinner_item, usageTypes);
        adapterUsage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUsage.setAdapter(adapterUsage);
    }

    private String getSelectedSpinnerValue(Spinner spinner) {
        int selectedItemPosition = spinner.getSelectedItemPosition();
        // If the selected position is the title which means didn't choose this tag
        if (selectedItemPosition == 0) {
            return "";
        } else {
            return spinner.getSelectedItem().toString();
        }
    }

}