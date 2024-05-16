package com.example.myapplication.src;

/**
 * Author: Yichi Zhang
 */
public class Tag {
    private String gender;
    private String masterCategory;
    private String subCategory;
    private String articleType;
    private String baseColour;
    private String season;
    private int year;
    private String usage;

    public Tag(String gender, String masterCategory, String subCategory, String articleType, String baseColour, String season, int year, String usage) {
        this.gender = gender;
        this.masterCategory = masterCategory;
        this.subCategory = subCategory;
        this.articleType = articleType;
        this.baseColour = baseColour;
        this.season = season;
        this.year = year;
        this.usage = usage;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMasterCategory() {
        return masterCategory;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public String getArticleType() {
        return articleType;
    }

    public String getBaseColour() {
        return baseColour;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public int getYear() {
        return year;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

}
