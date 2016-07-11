package com.example.mateuszdziubek.easysearch.usersearch.restdownload;

import com.example.mateuszdziubek.easysearch.usersearch.model.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface RestAPI {

    @GET("/users")
    Call<List<UserModel>> getUsers();
}
