package com.mogsev.simpleapp.network.loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.mogsev.simpleapp.model.Profile;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com).
 */

public class ProfileLoader extends AsyncTaskLoader<Profile> {
    private static final String TAG = ProfileLoader.class.getSimpleName();

    public ProfileLoader(Context context) {
        super(context);
    }

    @Override
    public Profile loadInBackground() {
        Log.i(TAG, "loadInBackground");
        Profile profile = new Profile();
        profile.setFirstName("Eugene");
        profile.setLastName("Sikaylo");
        return profile;
    }
}
