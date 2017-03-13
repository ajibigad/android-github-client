package com.ajibigad.juno.githubclient.network;

import android.app.ProgressDialog;

import com.ajibigad.juno.githubclient.model.GithubUser;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Path;

/**
 * Created by Julius on 08/03/2017.
 */
public class GithubSearchService extends Client implements GithubSearchInterface {

    private Retrofit client;
    private GithubSearchInterface searchService;

    public GithubSearchService(){
        setupRetrofit();
        searchService = client.create(GithubSearchInterface.class);
    }

    protected void setupRetrofit(){
        final OkHttpClient okHttpClient = okHttpClientBuilder.build();
        client = retroFitBuilder
                .client(okHttpClient)
                .build();
    }

    @Override
    public Call<GithubResponse> getLagosJavaDevs() {
        return searchService.getLagosJavaDevs();
    }

    @Override
    public Call<GithubResponse> getGithubUser(String username) {
        return searchService.getGithubUser(username);
    }
}
