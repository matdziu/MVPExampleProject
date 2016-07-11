package com.example.mateuszdziubek.easysearch.usersearch;


import com.example.mateuszdziubek.easysearch.usersearch.model.UserModel;
import com.example.mateuszdziubek.easysearch.usersearch.model.UsersCallback;

import java.util.List;


public class UserSearchPresenter implements UserSearchContract.UserActions {

    private UserSearchContract.View userSearchView;

    private List<String> users;

    public UserSearchPresenter(UserSearchContract.View userSearchView, List users) {
        this.userSearchView = userSearchView;
        this.users = users;
    }

    @Override
    public void loadData() {
        UsersCallback usersCallback = new UsersCallback() {
            @Override
            public void onResult(List<UserModel> result) {

                for(UserModel userModel : result) {
                    users.add(userModel.getLogin());
                }

                userSearchView.showPopulatedList(users);
            }

            @Override
            public void onError(Throwable error) {

            }
        };

        UsersRepository usersRepository = new UsersRepository();
        usersRepository.getUsers(usersCallback);

    }

    @Override
    public void searchForUser(String user) {
        userSearchView.fillEditText(user);

    }

}
