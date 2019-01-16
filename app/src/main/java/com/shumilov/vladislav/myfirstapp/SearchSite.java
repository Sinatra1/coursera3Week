package com.shumilov.vladislav.myfirstapp;

public class SearchSite {
    private String mTitle;

    private String mUrl;

    public SearchSite(String title, String url) {
        mTitle = title;
        mUrl = url;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }
}
