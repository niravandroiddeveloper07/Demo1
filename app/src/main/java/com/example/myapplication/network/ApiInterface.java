package com.example.myapplication.network;
 
import com.example.myapplication.model.Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
 

public interface ApiInterface {

    @GET("/api/users?")
    Call<Response> doGetUserList(@Query("page") String page);

}