package com.mogsev.githubinfo;

import android.content.Intent;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.mogsev.githubinfo.activity.RepositoryInfoActivity;
import com.mogsev.githubinfo.model.Repository;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com)
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class RepositoryInfoActivityTest {

    @Rule
    public ActivityTestRule<RepositoryInfoActivity> mActivityRule = new ActivityTestRule<>(RepositoryInfoActivity.class, false, false);

    @Test
    public void launchActivity_Success() {
        Repository repository = new Repository();
        repository.setName("google");
        repository.setForksCount(5);
        repository.setSubscribersUrl("https://api.github.com/repos/opauth/google/subscribers");
        Intent intent = new Intent();
        intent.putExtra(Repository.BUNDLE_NAME, repository);
        mActivityRule.launchActivity(intent);
    }
}
