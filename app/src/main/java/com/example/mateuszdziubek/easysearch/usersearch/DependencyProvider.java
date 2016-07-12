package com.example.mateuszdziubek.easysearch.usersearch;

public class DependencyProvider {

    private UserSearchContract.View userSearchView;

    public DependencyProvider(UserSearchContract.View userSearchView) {
        this.userSearchView = userSearchView;
    }

    public UserSearchContract.UserActions provideUserSearchPresenter() {
        return new UserSearchPresenter(userSearchView, new UsersRepository());
    }
}
