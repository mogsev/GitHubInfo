package com.mogsev.simpleapp.fragment;


import android.databinding.BaseObservable;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mogsev.simpleapp.MainActivity;
import com.mogsev.simpleapp.R;
import com.mogsev.simpleapp.databinding.FragmentFirstBinding;
import com.mogsev.simpleapp.viewmodel.ProfileViewModel;
import com.mogsev.simpleapp.viewmodel.ResultViewModel;

public class FirstFragment extends Fragment implements MainActivity.DataBindingContract {
    private static final String TAG = FirstFragment.class.getSimpleName();

    private static FirstFragment sInstance;

    private FragmentFirstBinding mBinding;
    private ResultViewModel mResult;
    private ProfileViewModel mProfile;

    public FirstFragment() {
    }

    public static FirstFragment newInstance() {
        if (sInstance == null) {
            sInstance = new FirstFragment();
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
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_first, container, false);
        View view = mBinding.getRoot();
        return view;
    }

    @Override
    public void setViewModel(BaseObservable viewModel) {
        Log.i(TAG, "setViewModel");
        if (viewModel instanceof ResultViewModel) {
            mResult = (ResultViewModel) viewModel;
            mBinding.setResult(mResult);
            return;
        }
        if (viewModel instanceof ProfileViewModel) {
            mProfile = (ProfileViewModel) viewModel;
            mBinding.setProfile(mProfile);
        }
    }

}
