package com.mogsev.githubinfo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.mogsev.githubinfo.R;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com)
 */

public class LoadingDataActivity extends AppCompatActivity {
    private static final String TAG = LoadingDataActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        setContentView(R.layout.activity_loading_data);
    }
}
