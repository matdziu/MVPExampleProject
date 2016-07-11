package com.example.mateuszdziubek.easysearch.usersearch;


import com.example.mateuszdziubek.easysearch.usersearch.model.UserModel;
import com.example.mateuszdziubek.easysearch.usersearch.model.UsersCallback;
import com.example.mateuszdziubek.easysearch.usersearch.restdownload.GitUsersListProvider;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersRepository implements UserSearchContract.IRepository {

    @Override
    public void getUsers(final UsersCallback usersCallback) {
        Callback<List<UserModel>> retrofitCallback = new Callback<List<UserModel>>() {
            @Override
            public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
//                Log.d("usersDownload", "success!");
                usersCallback.onResult(response.body());
            }

            @Override
            public void onFailure(Call<List<UserModel>> call, Throwable t) {
//                Log.d("usersDownload", "failure!");
            }
        };
        GitUsersListProvider gitUsersListProvider = new GitUsersListProvider();
        gitUsersListProvider.downloadUsers(retrofitCallback);
    }
}
