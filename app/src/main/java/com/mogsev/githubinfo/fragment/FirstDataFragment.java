package com.mogsev.githubinfo.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mogsev.githubinfo.R;
import com.mogsev.githubinfo.loader.ProfileLoader;
import com.mogsev.githubinfo.model.Profile;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com)
 */

public class FirstDataFragment extends Fragment implements LoaderManager.LoaderCallbacks<Profile>{
    private static final String TAG = FirstDataFragment.class.getSimpleName();

    private static FirstDataFragment sInstance;
    private Loader mLoader;

    private Button mButtonRunLoader;

    public FirstDataFragment() {
        // Required empty public constructor
    }

    public static FirstDataFragment newInstance() {
        if (sInstance == null) {
            sInstance = new FirstDataFragment();
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
        return inflater.inflate(R.layout.fragment_first_data, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "onViewCreated");
        mButtonRunLoader = (Button) view.findViewById(R.id.btn_start_loader);
        mButtonRunLoader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick mButtonRunLoader");
                if (mLoader == null) {
                    mLoader = getLoaderManager().initLoader(R.id.profile_loader, Bundle.EMPTY, FirstDataFragment.this);
                } else {
                    mLoader = getLoaderManager().restartLoader(R.id.profile_loader, Bundle.EMPTY, FirstDataFragment.this);
                }
            }
        });
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
