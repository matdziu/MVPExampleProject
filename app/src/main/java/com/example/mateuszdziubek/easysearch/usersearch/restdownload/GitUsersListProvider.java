package com.example.mateuszdziubek.easysearch.usersearch.restdownload;

import com.example.mateuszdziubek.easysearch.usersearch.model.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GitUsersListProvider {

    final String BASE_URL = "https://api.github.com";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    RestAPI service = retrofit.create(RestAPI.class);

    public void downloadUsers(Callback<List<UserModel>> callback) {
        Call<List<UserModel>> call = service.getUsers();
        call.enqueue(callback);

    }

}
