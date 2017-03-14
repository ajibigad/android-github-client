package com.ajibigad.juno.githubclient.network;

import com.ajibigad.juno.githubclient.model.GithubUser;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Julius on 08/03/2017.
 */
public interface GithubSearchInterface {

    @GET("search/users?q=language:java+location:lagos")
    public Call<GithubResponse> getLagosJavaDevs(@Query("page") int page);

    @GET("search/users")
    public Call<GithubResponse> getGithubUser(@Query("q") String username);
}
