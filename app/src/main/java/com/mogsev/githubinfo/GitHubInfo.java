package com.mogsev.githubinfo;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com)
 */

public class GitHubInfo extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //use vector drawables
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
}
