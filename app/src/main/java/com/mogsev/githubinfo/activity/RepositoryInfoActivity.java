package com.mogsev.githubinfo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mogsev.githubinfo.R;
import com.mogsev.githubinfo.adapter.SubscribersRvAdapter;
import com.mogsev.githubinfo.model.Owner;
import com.mogsev.githubinfo.model.Repository;
import com.mogsev.githubinfo.network.Network;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com)
 */

public class RepositoryInfoActivity extends AppCompatActivity {
    private static final String TAG = RepositoryInfoActivity.class.getSimpleName();

    private Repository mRepository;
    private RecyclerView mRecyclerView;
    private SubscribersRvAdapter mSubscribersRvAdapter;
    private ProgressBar mProgressBar;
    private TextView mTextViewNoResult;
    private TextView mCountOfSubscribers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        setContentView(R.layout.activity_repository_info);

        // initialize view elements
        initView();

        if (savedInstanceState != null) {
            mRepository = savedInstanceState.getParcelable(Repository.BUNDLE_NAME);
        } else {
            mRepository = getIntent().getExtras().getParcelable(Repository.BUNDLE_NAME);
        }

        // show name of repository
        getSupportActionBar().setTitle(mRepository.getName());

        if (TextUtils.isEmpty(mRepository.getSubscribersUrl())) { // check url
            mTextViewNoResult.setVisibility(View.VISIBLE);
        } else {
            mProgressBar.setVisibility(View.VISIBLE);
            Call<List<Owner>> call = Network.API_GIT_HUB.getListOfSubscribers(mRepository.getSubscribersUrl());
            call.enqueue(new Callback<List<Owner>>() {
                @Override
                public void onResponse(Call<List<Owner>> call, Response<List<Owner>> response) {
                    if (response.isSuccessful()) {
                        Log.i(TAG, "Response is successful: " + response.body().toString());
                        showSubscribers(response.body());
                    } else {
                        Network.showResponseErrorBody(TAG, response);
                    }
                    mProgressBar.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<List<Owner>> call, Throwable t) {
                    Log.e(TAG, "onFailure: " + t.getMessage());
                    if (mProgressBar != null) mProgressBar.setVisibility(View.GONE);
                }
            });
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState");
        outState.putParcelable(Repository.BUNDLE_NAME, mRepository);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void showSubscribers(List<Owner> list) {
        if (list != null && !list.isEmpty()) {
            mTextViewNoResult.setVisibility(View.GONE);
            mSubscribersRvAdapter.setList(list);
            mCountOfSubscribers.setText(getString(R.string.subscribers_count, list.size()));
        } else {
            mTextViewNoResult.setVisibility(View.VISIBLE);
        }
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // set toolbar home button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mCountOfSubscribers = (TextView) findViewById(R.id.tvCountOfSubscribers);
        mTextViewNoResult = (TextView) findViewById(R.id.tvNoResult);
        mRecyclerView = (RecyclerView) findViewById(R.id.rvDefault);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), layoutManager.getOrientation()));
        mSubscribersRvAdapter = new SubscribersRvAdapter(RepositoryInfoActivity.this);
        mRecyclerView.setAdapter(mSubscribersRvAdapter);
    }
}
