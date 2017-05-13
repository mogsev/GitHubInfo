package com.mogsev.appdatabinding;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mogsev.appdatabinding.adapter.RepositoriesRvAdapter;
import com.mogsev.appdatabinding.github.GitHubRepositoriesSearch;
import com.mogsev.appdatabinding.model.Repository;
import com.mogsev.appdatabinding.model.SearchRepositoriesResult;
import com.mogsev.appdatabinding.network.Network;
import com.mogsev.appdatabinding.utils.PaginationHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com)
 */

public class MainActivity extends AppCompatActivity implements GitHubRepositoriesSearch {
    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String BUNDLE_QUERY_MAP = "query_map";
    private static final String QUERY = "q";
    private static final String QUERY_PAGE = "page";

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private RepositoriesRvAdapter mRepositoriesRvAdapter;
    private ProgressBar mProgressBar;
    private TextView mTextViewSaver;

    private boolean mSearch = false;

    private Map<String, String> mQueryMap;

    private int mPageCount;
    private boolean mEndPage = false;
    private boolean mLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        setContentView(R.layout.activity_main);

        // initialize view elements
        initView();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState");
        outState.putSerializable(BUNDLE_QUERY_MAP, (HashMap) mQueryMap); // save query map
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "onRestoreInstanceState");
        mQueryMap = (HashMap) savedInstanceState.getSerializable(BUNDLE_QUERY_MAP); // restore query map
        if (mQueryMap.containsKey(QUERY_PAGE)) mQueryMap.remove(QUERY_PAGE); // remove query page
        searchRepositories(mQueryMap);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        Log.i(TAG, "onCreateOptionsMenu");
        getMenuInflater().inflate(R.menu.menu_fragment_repo_info, menu);
        MenuItem search = (MenuItem) menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) search.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.i(TAG, "onQueryTextSubmit: " + query);
                searchView.setIconifiedByDefault(true);
                if (!mSearch) { // check if search is running
                    searchRepositories(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // empty
                return false;
            }
        });

        return true;
    }

    @Override
    public void searchRepositories(String query) {
        if (TextUtils.isEmpty(query)) {
            Toast.makeText(MainActivity.this, R.string.search_toast_warning_query_is_empty, Toast.LENGTH_SHORT).show();
            return;
        }
        // break pagination
        mEndPage = false;
        mLoading = false;
        mPageCount = 0;

        // start search
        mSearch = true;
        mProgressBar.setVisibility(View.VISIBLE);
        if (mQueryMap == null) {
            mQueryMap = new HashMap<>();
        } else {
            mQueryMap.clear();
        }
        mQueryMap.put(QUERY, query);
        getSupportActionBar().setTitle(getString(R.string.title_search_query, query));
        onCall(mQueryMap);
    }

    @Override
    public void searchRepositories(Map<String, String> map) {
        if (map == null) {
            Log.e(TAG, "QueryMap cannot be null");
            return;
        }
        mSearch = true;
        mProgressBar.setVisibility(View.VISIBLE);
        getSupportActionBar().setTitle(getString(R.string.title_search_query, map.get(QUERY)));
        onCall(mQueryMap);
    }

    @Override
    public void onCall(Map<String, String> queryMap) {
        if (queryMap == null) {
            throw new IllegalArgumentException("QueryMap cannot be null");
        }
        Call<SearchRepositoriesResult> call = Network.API_GIT_HUB.searchRepositories(queryMap);
        call.enqueue(new Callback<SearchRepositoriesResult>() {
            @Override
            public void onResponse(Call<SearchRepositoriesResult> call, Response<SearchRepositoriesResult> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "Response is successful: " + response.body().toString());
                    showRepositories(response.body().getItems());
                } else {
                    Network.showResponseErrorBody(TAG, response);
                }
                mProgressBar.setVisibility(View.GONE);
                mSearch = false;
            }

            @Override
            public void onFailure(Call<SearchRepositoriesResult> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
                if (mProgressBar != null) mProgressBar.setVisibility(View.GONE);
                mSearch = false;
            }
        });
    }

    @Override
    public void showRepositories(List<Repository> list) {
        mRepositoriesRvAdapter.setList(list);
        mTextViewSaver.setText(R.string.search_repository_no_result);
        mTextViewSaver.setVisibility(list.isEmpty() ? View.VISIBLE : View.GONE);
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mTextViewSaver = (TextView) findViewById(R.id.tvSaver);
        mRecyclerView = (RecyclerView) findViewById(R.id.rvDefault);
        mLinearLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), mLinearLayoutManager.getOrientation()));
        mRepositoriesRvAdapter = new RepositoriesRvAdapter(MainActivity.this);
        mRecyclerView.setAdapter(mRepositoriesRvAdapter);
        // set scroll listener for pagination
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    if (!mLoading && !mEndPage) {
                        if (PaginationHelper.isDownPagination(mLinearLayoutManager)) {
                            startPagination();
                        }
                    }
                }
            }
        });
    }

    private void startPagination() {
        Log.i(TAG, "Pagination down start...");
        mLoading = true;
        if (mPageCount == 0) {
            mPageCount = 2;
        } else {
            mPageCount = mPageCount + 1;
        }
        mRepositoriesRvAdapter.addProgress();
        mQueryMap.put(QUERY_PAGE, String.valueOf(mPageCount));
        Call<SearchRepositoriesResult> call = Network.API_GIT_HUB.searchRepositories(mQueryMap);
        call.enqueue(new Callback<SearchRepositoriesResult>() {
            @Override
            public void onResponse(Call<SearchRepositoriesResult> call, Response<SearchRepositoriesResult> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "Response is successful: " + response.body().toString());
                    mRepositoriesRvAdapter.removeProgress();
                    List<Repository> list = response.body().getItems();
                    if (list != null && !list.isEmpty()) {
                        for (Repository repository : list) {
                            mRepositoriesRvAdapter.addItem(repository, mRepositoriesRvAdapter.getItemCount());
                        }
                    } else {
                        mPageCount = 0;
                        mEndPage = true;
                    }
                } else {
                    Network.showResponseErrorBody(TAG, response);
                }
                mLoading = false;
            }

            @Override
            public void onFailure(Call<SearchRepositoriesResult> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
                mLoading = false;
            }
        });
    }
}
