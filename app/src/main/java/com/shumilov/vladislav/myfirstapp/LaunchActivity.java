package com.shumilov.vladislav.myfirstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LaunchActivity extends AppCompatActivity {
    private EditText mEditView;
    private Button mButtonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        mEditView = findViewById(R.id.editView);
        mButtonView = findViewById(R.id.buttonView);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mEditView.getText())) {
                    return;
                }

                Toast.makeText(LaunchActivity.this, mEditView.getText(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
