package com.mogsev.githubinfo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mogsev.githubinfo.R;
import com.mogsev.githubinfo.loader.ProfileLoader;
import com.mogsev.githubinfo.model.Profile;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com)
 */

public class SecondDataFragment extends Fragment implements LoaderManager.LoaderCallbacks<Profile> {
    private static final String TAG = SecondDataFragment.class.getSimpleName();

    private static SecondDataFragment sInstance;

    public SecondDataFragment() {
        // Required empty public constructor
    }

    public static SecondDataFragment newInstance() {
        if (sInstance == null) {
            sInstance = new SecondDataFragment();
        }
        return sInstance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");
        return inflater.inflate(R.layout.fragment_second_data, container, false);
    }

    @Override
    public Loader<Profile> onCreateLoader(int id, Bundle args) {
        Log.i(TAG, "onCreateLoader");
        return new ProfileLoader(getContext());
    }

    @Override
    public void onLoadFinished(Loader<Profile> loader, Profile data) {
        Log.i(TAG, "onLoadFinished");
    }

    @Override
    public void onLoaderReset(Loader<Profile> loader) {
        Log.i(TAG, "onLoaderReset");
    }
}
