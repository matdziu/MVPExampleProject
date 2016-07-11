package com.example.mateuszdziubek.easysearch.usersearch;

import com.example.mateuszdziubek.easysearch.usersearch.model.UserModel;

import java.util.List;

import retrofit2.Callback;

public interface UserSearchContract {

    interface View {

        void showPopulatedList(List<String> users);

        void fillEditText(String text);
    }

    interface UserActions {

        void loadData();

        void searchForUser(String user);
    }

    interface IRepository {

        void getUsers(Callback<List<UserModel>> callback);

    }
}
