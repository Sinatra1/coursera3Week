package com.shumilov.vladislav.myfirstapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class SharedPreferencesHelper {
    private static final String SHARED_PREFERENCES_NAME = "SHARED_PREFERENCES_NAME";
    private static final String CURRENT_SEARCH_SITE_KEY = "CURRENT_SEARCH_SITE_KEY";

    private SharedPreferences mSharedPreferences;
    private List<SearchSite> mSearchSites;
    private Gson gson = new Gson();

    public SharedPreferencesHelper(@NonNull Context context) {
        mSharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);

        setSearchSites();
    }

    public @Nullable SearchSite getCurrentSearchSite() {
        SearchSite searchSite = gson.fromJson(mSharedPreferences.getString(CURRENT_SEARCH_SITE_KEY, ""), SearchSite.class);

        if (searchSite != null) {
            return searchSite;
        }

        searchSite = setCurrentSearchSite(mSearchSites.get(0).getTitle());

        return searchSite;
    }

    public @Nullable SearchSite setCurrentSearchSite(@NonNull String searchSiteTitle) {
        SearchSite searchSite = getSearchSiteByTitle(searchSiteTitle);

        if (searchSite == null) {
            return null;
        }

        mSharedPreferences.edit()
                .putString(CURRENT_SEARCH_SITE_KEY, gson.toJson(searchSite))
                .apply();

        return searchSite;
    }

    public @NonNull List<SearchSite> getSearchSites() {
        return mSearchSites;
    }

    protected SearchSite getSearchSiteByTitle(@NonNull String title) {
        for (SearchSite searchSite: mSearchSites) {
            if (searchSite.getTitle().equals(title)) {
                return searchSite;
            }
        }

        return null;
    }

    protected void setSearchSites() {
        mSearchSites = new ArrayList<>();
        mSearchSites.add(new SearchSite("Google", "http://google.com/#q="));
        mSearchSites.add(new SearchSite("Yandex", "http://yandex.ru/#q="));
        mSearchSites.add(new SearchSite("Bing", "http://bing.com/#q="));
    }

}
