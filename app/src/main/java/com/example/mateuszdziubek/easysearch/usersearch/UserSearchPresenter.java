package com.example.mateuszdziubek.easysearch.usersearch;


import android.util.Log;

import com.example.mateuszdziubek.easysearch.usersearch.model.UserModel;
import com.example.mateuszdziubek.easysearch.usersearch.restdownload.GitUsersListProvider;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserSearchPresenter implements UserSearchContract.UserActions {

    private UserSearchContract.View userSearchView;
    private List<String> users;

    public UserSearchPresenter(UserSearchContract.View userSearchView, List users) {
        this.userSearchView = userSearchView;
        this.users = users;
    }

    @Override
    public void loadData() {
        GitUsersListProvider gitUsersListProvider = new GitUsersListProvider();
        Callback<List<UserModel>> callback = new Callback<List<UserModel>>() {
            @Override
            public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
                Log.d("usersDownload", "success!");

                for(UserModel userModel : response.body()) {
                    users.add(userModel.getLogin());
                }

                userSearchView.showPopulatedList(users);

            }

            @Override
            public void onFailure(Call<List<UserModel>> call, Throwable t) {
                Log.d("usersDownload", "failure!");
            }
        };

        gitUsersListProvider.downloadUsers(callback);
    }

    @Override
    public void searchForUser(String user) {
        userSearchView.fillEditText(user);

    }

}
