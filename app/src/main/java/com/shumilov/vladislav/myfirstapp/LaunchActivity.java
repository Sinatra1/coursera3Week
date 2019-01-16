package com.shumilov.vladislav.myfirstapp;

import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class LaunchActivity extends SingleFragmentActivity {

    private String currentFragmentId;

    @Override
    protected Fragment getFragment() {
        if (currentFragmentId == null) {
            currentFragmentId = MainFragment.class.getName();
        }

        switch (currentFragmentId) {
            
        }

        return new MainFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.launch_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_LONG).show();

        return true;
    }
}
