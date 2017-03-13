package com.ajibigad.juno.githubclient.network;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Julius on 08/03/2017.
 */
public class Client {

    // set defaults for all builders
    protected OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder()
            .addNetworkInterceptor(new StethoInterceptor());

    public OkHttpClient OkHttpClient(){
        return okHttpClientBuilder.build();
    }

    protected Retrofit.Builder retroFitBuilder = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(GithubEndpoints.API);
}
