package com.example.mateuszdziubek.easysearch.usersearch;


import android.widget.Toast;

import com.example.mateuszdziubek.easysearch.usersearch.model.RepositoryCallback;
import com.example.mateuszdziubek.easysearch.usersearch.model.UserModel;
import com.example.mateuszdziubek.easysearch.usersearch.model.UsersCallback;

import java.util.ArrayList;
import java.util.List;


public class UserSearchPresenter implements UserSearchContract.UserActions {

    private UserSearchContract.View userSearchView;

    private UserSearchContract.Repository usersRepository;

    private List<String> users = new ArrayList<>();

    public UserSearchPresenter(UserSearchContract.View userSearchView,
                               UserSearchContract.Repository usersRepository) {
        this.userSearchView = userSearchView;
        this.usersRepository = usersRepository;
    }

    @Override
    public void loadData() {
        RepositoryCallback usersCallback = new UsersCallback() {
            @Override
            public void onResult(List<UserModel> result) {

                if (result.size() > 0) {
                    for(UserModel userModel : result) {
                        users.add(userModel.getLogin());
                    }

                    userSearchView.showPopulatedList(users);
                }
                else {
                    throwException();
                }
            }

            @Override
            public void onError(Throwable error) {

            }
        };

        usersRepository.getUsers(usersCallback);

    }

    @Override
    public void searchForUser(String user) {
        userSearchView.fillEditText(user);

    }

    public void throwException() {
        throw new NullPointerException("no data in results");
    }

}
