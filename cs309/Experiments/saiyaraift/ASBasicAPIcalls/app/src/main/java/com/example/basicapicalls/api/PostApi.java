package com.example.basicapicalls.api;

import com.example.basicapicalls.model.Post;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PostApi{

    @GET("/posts/1")
    Call<Post> getFirstPost();

}