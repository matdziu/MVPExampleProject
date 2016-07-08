package com.example.mateuszdziubek.easysearch.usersearch;

import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class UserSearchPresenter implements UserSearchContract.UserActions {

    UserSearchContract.View userSearchView;
    List users;
    ListView listView;

    public UserSearchPresenter(UserSearchContract.View userSearchView, List users,
                               ListView listView) {
        this.userSearchView = userSearchView;
        this.users = users;
        this.listView = listView;
    }

    @Override
    public void searchForUser(EditText editText) {
        userSearchView.applyDynamicSearch(editText, listView);
    }

    @Override
    public void startApplication() {
        userSearchView.showPopulatedList(users);
    }
}
