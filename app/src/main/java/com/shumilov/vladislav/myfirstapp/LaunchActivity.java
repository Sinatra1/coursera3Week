package com.shumilov.vladislav.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LaunchActivity extends AppCompatActivity {
    private EditText mEditView;
    private Button mButtonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        mEditView = findViewById(R.id.textView);
        mButtonView = findViewById(R.id.buttonView);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mButtonView == null) {
            return;
        }

        mButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEditView == null || TextUtils.isEmpty(mEditView.getText())) {
                    return;
                }

                Intent intent = SecondActivity.newIntent(LaunchActivity.this, mEditView.getText().toString());

                startActivity(intent);
            }
        });
    }
}
