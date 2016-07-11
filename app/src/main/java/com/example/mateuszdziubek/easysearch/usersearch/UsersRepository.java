package com.example.mateuszdziubek.easysearch.usersearch;


import com.example.mateuszdziubek.easysearch.usersearch.model.UserModel;
import com.example.mateuszdziubek.easysearch.usersearch.restdownload.GitUsersListProvider;

import java.util.List;

import retrofit2.Callback;

public class UsersRepository implements UserSearchContract.IRepository {

    @Override
    public void getUsers(Callback<List<UserModel>> callback) {
        GitUsersListProvider gitUsersListProvider = new GitUsersListProvider();
        gitUsersListProvider.downloadUsers(callback);
    }
}
