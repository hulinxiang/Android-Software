package com.example.myapplication.src;

/**
 * The Tag class represents a tag that can be associated with an item.
 * Each tag has several properties including gender, master category, sub category, article type, base colour, season, year, and usage.
 *
 * @author Yichi Zhang
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

    /**
     * Constructs a new Tag with the specified values.
     *
     * @param gender the gender associated with the tag
     * @param masterCategory the master category associated with the tag
     * @param subCategory the sub category associated with the tag
     * @param articleType the article type associated with the tag
     * @param baseColour the base colour associated with the tag
     * @param season the season associated with the tag
     * @param year the year associated with the tag
     * @param usage the usage associated with the tag
     */
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

    /**
     * Returns the gender associated with the tag.
     *
     * @return the gender associated with the tag
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the gender associated with the tag.
     *
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Returns the master category associated with the tag.
     *
     * @return the master category associated with the tag
     */
    public String getMasterCategory() {
        return masterCategory;
    }

    /**
     * Returns the sub category associated with the tag.
     *
     * @return the sub category associated with the tag
     */
    public String getSubCategory() {
        return subCategory;
    }

    /**
     * Returns the article type associated with the tag.
     *
     * @return the article type associated with the tag
     */
    public String getArticleType() {
        return articleType;
    }

    /**
     * Returns the base colour associated with the tag.
     *
     * @return the base colour associated with the tag
     */
    public String getBaseColour() {
        return baseColour;
    }

    /**
     * Returns the season associated with the tag.
     *
     * @return the season associated with the tag
     */
    public String getSeason() {
        return season;
    }

    /**
     * Sets the season associated with the tag.
     *
     * @param season the season to set
     */
    public void setSeason(String season) {
        this.season = season;
    }

    /**
     * Returns the year associated with the tag.
     *
     * @return the year associated with the tag
     */
    public int getYear() {
        return year;
    }

    /**
     * Returns the usage associated with the tag.
     *
     * @return the usage associated with the tag
     */
    public String getUsage() {
        return usage;
    }

    /**
     * Sets the usage associated with the tag.
     *
     * @param usage the usage to set
     */
    public void setUsage(String usage) {
        this.usage = usage;
    }

}