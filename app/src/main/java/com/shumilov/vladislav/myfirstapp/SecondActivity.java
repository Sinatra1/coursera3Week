package com.shumilov.vladislav.myfirstapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    private TextView mTextView;
    private Button mButtonView;

    private static final String TEXT_KEY = "TEXT_KEY";

    public static Intent newIntent(Context context, String text) {
        if (context == null) {
            return null;
        }

        Intent intent = new Intent(context, SecondActivity.class);

        if (!TextUtils.isEmpty(text)) {
            intent.putExtra(TEXT_KEY, text);
        }

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mTextView = findViewById(R.id.textView);
        mButtonView = findViewById(R.id.buttonView);

        String text = getIntent().getStringExtra(TEXT_KEY);

        if (mTextView != null && !TextUtils.isEmpty(text)) {
            mTextView.setText(text);
        }
    }
}
