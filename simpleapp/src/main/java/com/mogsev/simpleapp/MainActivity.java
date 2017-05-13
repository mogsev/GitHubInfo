package com.mogsev.simpleapp;

import android.databinding.BaseObservable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.mogsev.simpleapp.model.Profile;
import com.mogsev.simpleapp.network.loaders.ProfileLoader;
import com.mogsev.simpleapp.viewmodel.ProfileViewModel;
import com.mogsev.simpleapp.viewmodel.ResultViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Profile> {
    private static final String TAG = MainActivity.class.getSimpleName();

    private ResultViewModel mResult;
    private ProfileViewModel mProfile;
    private Loader mLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        setContentView(R.layout.activity_main);
        mResult = new ResultViewModel();
        mProfile = new ProfileViewModel();

        mLoader = getSupportLoaderManager().getLoader(R.id.profile_loader);
        if (mLoader == null) {
            mLoader = getSupportLoaderManager().initLoader(R.id.profile_loader, Bundle.EMPTY, this);
        }
        mLoader.forceLoad();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");

        putViewModelToFragment(mResult);
        putViewModelToFragment(mProfile);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState");
        mProfile.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "onRestoreInstanceState");
        mProfile.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public Loader<Profile> onCreateLoader(int id, Bundle args) {
        return new ProfileLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<Profile> loader, Profile data) {
        Log.i(TAG, "onLoadFinished");
        if (data != null) {
            mProfile.setModel(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<Profile> loader) {
        Log.e(TAG, "onLoaderReset");
    }

    protected void putViewModelToFragment(BaseObservable viewModel) {
        List<Fragment> listFragments = getSupportFragmentManager().getFragments();
        if (listFragments != null && !listFragments.isEmpty()) {
            for (Fragment fragment : listFragments) {
                if (fragment != null && fragment instanceof DataBindingContract) {
                    ((DataBindingContract) fragment).setViewModel(viewModel);
                }
            }
        }
    }

    public interface DataBindingContract {
        public void setViewModel(BaseObservable viewModel);
    }
}
