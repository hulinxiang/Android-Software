package com.example.myapplication.src;

public class Tag {
    private String gender;
    private MasterCategory masterCategory;
    private String baseColour;
    private String season;
    private int year;
    private String usage;

    public Tag(String gender, MasterCategory masterCategory, String baseColour, String season, int year, String usage) {
        this.gender = gender;
        this.masterCategory = masterCategory;
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

    public MasterCategory getMasterCategory() {
        return masterCategory;
    }

    public void setMasterCategory(MasterCategory masterCategory) {
        this.masterCategory = masterCategory;
    }

    public String getBaseColour() {
        return baseColour;
    }

    public void setBaseColour(String baseColour) {
        this.baseColour = baseColour;
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

    public void setYear(int year) {
        this.year = year;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }


    public static class MasterCategory {
        private String masterCategoryName;
        private SubCategory subCategory;

        public MasterCategory(String masterCategoryName, SubCategory subCategory) {
            this.masterCategoryName = masterCategoryName;
            this.subCategory = subCategory;
        }

        public String getMasterCategoryName() {
            return masterCategoryName;
        }

        public void setMasterCategoryName(String masterCategoryName) {
            this.masterCategoryName = masterCategoryName;
        }

        public SubCategory getSubCategory() {
            return subCategory;
        }

        public void setSubCategory(SubCategory subCategory) {
            this.subCategory = subCategory;
        }
    }

    public static class SubCategory {
        private String subCategoryName;
        private ArticleType articleType;

        public SubCategory(String subCategoryName, ArticleType articleType) {
            this.subCategoryName = subCategoryName;
            this.articleType = articleType;
        }

        public String getSubCategoryName() {
            return subCategoryName;
        }

        public void setSubCategoryName(String subCategoryName) {
            this.subCategoryName = subCategoryName;
        }

        public ArticleType getArticleType() {
            return articleType;
        }

        public void setArticleType(ArticleType articleType) {
            this.articleType = articleType;
        }


    }

    public static class ArticleType {
        private String articleTypeName;

        public ArticleType(String articleTypeName) {
            this.articleTypeName = articleTypeName;
        }

        public String getArticleTypeName() {
            return articleTypeName;
        }

        public void setArticleTypeName(String articleTypeName) {
            this.articleTypeName = articleTypeName;
        }
    }
}
