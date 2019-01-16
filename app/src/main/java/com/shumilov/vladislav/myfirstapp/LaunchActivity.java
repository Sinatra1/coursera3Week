package com.shumilov.vladislav.myfirstapp;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class LaunchActivity extends SingleFragmentActivity {

    private String currentFragmentName;

    @Override
    protected @NonNull Fragment getFragment() {
        if (currentFragmentName == null) {
            currentFragmentName = MainFragment.class.getName();
        }

        if (currentFragmentName.equals(PreferencesFragment.class.getName())) {
            return PreferencesFragment.getInstance();
        }

        if (currentFragmentName.equals(SearchFragment.class.getName())) {
            return SearchFragment.getInstance();
        }

        return MainFragment.getInstance();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.launch_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.exitAction:
                finish();
                break;
            case R.id.preferencesAction:
                currentFragmentName = PreferencesFragment.class.getName();
                showFragment();
                break;
            case R.id.searchAction:
                currentFragmentName = SearchFragment.class.getName();
                showFragment();
                break;
        }

        return true;
    }
}
