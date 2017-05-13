package com.mogsev.simpleapp.fragment;


import android.databinding.BaseObservable;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mogsev.simpleapp.MainActivity;
import com.mogsev.simpleapp.R;
import com.mogsev.simpleapp.databinding.FragmentSecondBinding;
import com.mogsev.simpleapp.viewmodel.ProfileViewModel;
import com.mogsev.simpleapp.viewmodel.ResultViewModel;

public class SecondFragment extends Fragment implements MainActivity.DataBindingContract {
    private static final String TAG = SecondFragment.class.getSimpleName();

    private static SecondFragment sInstance;

    private FragmentSecondBinding mBinding;
    private ResultViewModel mResult;
    private ProfileViewModel mProfile;

    public SecondFragment() {
    }

    public static SecondFragment newInstance() {
        if (sInstance == null) {
            sInstance = new SecondFragment();
        }
        return sInstance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_second, container, false);
        View view = mBinding.getRoot();
        return view;
    }

    @Override
    public void setViewModel(BaseObservable viewModel) {
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
