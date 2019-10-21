package com.example.myapplication.network;
 
import com.example.myapplication.model.UserListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
 

public interface ApiInterface {

    @GET("/api/users?")
    Call<UserListResponse> doGetUserList(@Query("page") String page);

}