package com.mogsev.githubinfo.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.mogsev.githubinfo.model.Profile;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com)
 */

public class ProfileLoader extends AsyncTaskLoader<Profile> {
    private static final String TAG = ProfileLoader.class.getSimpleName();

    public ProfileLoader(Context context) {
        super(context);
        Log.i(TAG, "ProfileLoader");
    }

    @Override
    public Profile loadInBackground() {
        Log.i(TAG, "loadInBackground");
        return null;
    }
}
