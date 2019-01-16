package com.shumilov.vladislav.myfirstapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public abstract class SingleFragmentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ac_single_fragment);

        if (savedInstanceState == null) {
            showFragment();
        }
    }

    protected void showFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = getFragment();

        Fragment foundedFragment = fragmentManager.findFragmentByTag(fragment.getClass().getName());

        if (foundedFragment != null) {
            fragmentManager.popBackStack(foundedFragment.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }

        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment, fragment.getClass().getName())
                .addToBackStack(fragment.getClass().getName())
                .commit();
    }

    protected abstract Fragment getFragment();

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (fragmentManager.getBackStackEntryCount() == 1) {
            finish();
            return;
        }

        fragmentManager.popBackStack();
    }
}
