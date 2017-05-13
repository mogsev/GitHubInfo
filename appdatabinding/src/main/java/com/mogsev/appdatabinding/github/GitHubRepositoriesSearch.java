package com.mogsev.appdatabinding.github;

import com.mogsev.appdatabinding.model.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com)
 */

public interface GitHubRepositoriesSearch {

    public void searchRepositories(String query);

    public void searchRepositories(Map<String, String> map);

    public void onCall(Map<String, String> queryMap);

    public void showRepositories(List<Repository> list);

}
