package com.example.mateuszdziubek.easysearch.usersearch;

import android.widget.EditText;
import android.widget.ListView;


import java.util.List;

public class UserSearchPresenter implements UserSearchContract.UserActions {

    UserSearchContract.View userSearchView;
    List<String> users;
    ListView listView;
    EditText editText;

    public UserSearchPresenter(UserSearchContract.View userSearchView, List users,
                               ListView listView, EditText editText) {
        this.userSearchView = userSearchView;
        this.users = users;
        this.listView = listView;
        this.editText = editText;
    }

    @Override
    public void startApplication() {
        userSearchView.showPopulatedList(users, listView);
    }

    @Override
    public void searchForUser(String user) {
        editText.setText(user);

    }
}
