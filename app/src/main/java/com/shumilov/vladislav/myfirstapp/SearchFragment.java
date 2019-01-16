package com.shumilov.vladislav.myfirstapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class SearchFragment extends Fragment {

    public static SearchFragment getInstance() {
        return new SearchFragment();
    }

    private TextView mQueryEditView;
    private Button mSearchButtonView;
    private SharedPreferencesHelper mSharedPreferencesHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.fr_search, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mSharedPreferencesHelper = new SharedPreferencesHelper(getActivity());

        mQueryEditView = view.findViewById(R.id.etQuery);
        mSearchButtonView = view.findViewById(R.id.btSearch);

        if (mSearchButtonView == null || mQueryEditView == null || mSharedPreferencesHelper == null) {
            return;
        }

        mSearchButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchInBrowser(mQueryEditView.getText().toString());
            }
        });
    }

    private void searchInBrowser(String query) {
        if (TextUtils.isEmpty(query)) {
            Toast.makeText(getActivity(), R.string.query_required, Toast.LENGTH_SHORT).show();
            return;
        }

        String escapedQuery = "";

        try {
            escapedQuery = URLEncoder.encode(query, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Log.e(SearchFragment.class.getSimpleName(), e.getMessage());
        }

        String url = mSharedPreferencesHelper.getCurrentSearchSite().getUrl() + escapedQuery;

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));

        startActivity(intent);
    }
}
