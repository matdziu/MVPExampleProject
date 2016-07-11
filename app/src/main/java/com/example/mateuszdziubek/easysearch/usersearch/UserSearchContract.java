package com.example.mateuszdziubek.easysearch.usersearch;

import com.example.mateuszdziubek.easysearch.usersearch.model.RepositoryCallback;
import com.example.mateuszdziubek.easysearch.usersearch.model.UserModel;

import java.util.List;


public interface UserSearchContract {

    interface View {

        void showPopulatedList(List<String> users);

        void fillEditText(String text);
    }

    interface UserActions {

        void loadData();

        void searchForUser(String user);
    }

    interface Repository {

        void getUsers(RepositoryCallback<List<UserModel>> usersCallback);

    }
}
