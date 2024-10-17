package com.example.vjezba2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GitHubApiService {
    @GET("search/repositories")
    Call<GitHubResponse> getTopRepositories(@Query("q") String query);
}