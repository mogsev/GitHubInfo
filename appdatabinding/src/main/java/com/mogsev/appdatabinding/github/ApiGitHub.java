package com.mogsev.appdatabinding.github;

import com.mogsev.appdatabinding.model.Owner;
import com.mogsev.appdatabinding.model.SearchRepositoriesResult;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com)
 */

public interface ApiGitHub {
    public static final String BASE_URL = "https://api.github.com";

    public static final String HEADER_ACCEPT_APP_JSON = "Accept: application/json";
    public static final String HEADER_CONTENT_TYPE_APP_JSON = "Content-type: application/json";

    @Headers({HEADER_ACCEPT_APP_JSON, HEADER_CONTENT_TYPE_APP_JSON})
    @GET("search/repositories")
    Call<SearchRepositoriesResult> getCallOfSearchRepositories(@QueryMap Map<String, String> options);

    @Headers({HEADER_ACCEPT_APP_JSON, HEADER_CONTENT_TYPE_APP_JSON})
    @GET
    Call<List<Owner>> getListOfSubscribers(@Url String url);

    @Headers({HEADER_ACCEPT_APP_JSON, HEADER_CONTENT_TYPE_APP_JSON})
    @GET("search/repositories")
    Observable<SearchRepositoriesResult> getObservableOfSearchRepositories(@QueryMap Map<String, String> options);
}
