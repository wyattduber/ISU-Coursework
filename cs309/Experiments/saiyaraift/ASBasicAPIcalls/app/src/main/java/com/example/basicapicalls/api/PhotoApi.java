package com.example.basicapicalls.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import com.example.basicapicalls.model.Photo;

import java.util.List;

public interface PhotoApi {


    @GET("/photos/1")
    Call<Photo> getFirstPhoto();

    //dealing with arrays
    @GET("photos")
    Call<List<Photo>> getAllPhoto();

    @GET("photos/{photoNum}")
    Call<Photo> getPhotoByNum(@Path("photoNum") String photoNum);



}
