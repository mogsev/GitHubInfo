package com.mogsev.githubinfo.github;

import com.mogsev.githubinfo.model.Owner;
import com.mogsev.githubinfo.model.SearchRepositoriesResult;
import com.mogsev.githubinfo.network.Network;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com)
 */

public class ApiGitHubTest extends Assert {
    private static final String TAG = ApiGitHubTest.class.getSimpleName();

    @Test
    public void searchRepositories_Success() {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("q", "google");
        Call<SearchRepositoriesResult> call = Network.API_GIT_HUB.searchRepositories(queryMap);
        try {
            Response<SearchRepositoriesResult> response = call.execute();
            SearchRepositoriesResult result = response.body();
            assertTrue(response.isSuccessful());
            System.out.println(TAG + " Body: " + result.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void getListOfSubscribers_Success() {
        String url = "https://api.github.com/repos/opauth/google/subscribers";
        Call<List<Owner>> call = Network.API_GIT_HUB.getListOfSubscribers(url);
        try {
            Response<List<Owner>> response = call.execute();
            List<Owner> list = response.body();
            assertTrue(response.isSuccessful());
            System.out.println(TAG + " Body: " + list);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
