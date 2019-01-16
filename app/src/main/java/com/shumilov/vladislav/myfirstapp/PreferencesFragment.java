package com.shumilov.vladislav.myfirstapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.List;

public class PreferencesFragment extends Fragment {

    public static PreferencesFragment getInstance() {
        return new PreferencesFragment();
    }

    private RadioGroup mRadioGroup;
    private SharedPreferencesHelper mSharedPreferencesHelper;
    private SearchSite mCurrentSearchSite;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.fr_perferences, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRadioGroup = view.findViewById(R.id.searchSitesRadioGroup);

        mSharedPreferencesHelper = new SharedPreferencesHelper(getActivity());

        if (mRadioGroup == null || mSharedPreferencesHelper == null) {
            return;
        }

        fillRadioGroup();

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = group.findViewById(checkedId);
                mCurrentSearchSite = mSharedPreferencesHelper.setCurrentSearchSite(radioButton.getText().toString());
            }
        });
    }

    private void fillRadioGroup() {
        List<SearchSite> searchSites = mSharedPreferencesHelper.getSearchSites();

        for (SearchSite searchSite: searchSites) {
            RadioButton radioButton = new RadioButton(new ContextThemeWrapper(getActivity(), R.style.MyRadioButton), null, 0);
            radioButton.setText(searchSite.getTitle());
            radioButton.setTag(searchSite.getTitle());
            mRadioGroup.addView(radioButton);
        }

        setCurrentSearchSite();
    }

    private void setCurrentSearchSite() {
        mCurrentSearchSite = mSharedPreferencesHelper.getCurrentSearchSite();

        RadioButton radioButton = mRadioGroup.findViewWithTag(mCurrentSearchSite.getTitle());

        if (radioButton == null) {
            return;
        }

        radioButton.setChecked(true);
    }
}
