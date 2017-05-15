package com.mogsev.simpleapp.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mogsev.simpleapp.R;
import com.mogsev.simpleapp.widget.CircleBar;
import com.mogsev.simpleapp.widget.CircleBar.CircleBarAngleAnimation;

public class CircleActivity extends AppCompatActivity {
    private static final String TAG = CircleActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle);

        CircleBar circleBar = (CircleBar) findViewById(R.id.circle_bar);
        CircleBarAngleAnimation angleAnimation = new CircleBarAngleAnimation(circleBar, 240);

        new Handler().postDelayed(() -> {
            angleAnimation.setDuration(1000);
            circleBar.startAnimation(angleAnimation);
        }, 1000);

    }

}
