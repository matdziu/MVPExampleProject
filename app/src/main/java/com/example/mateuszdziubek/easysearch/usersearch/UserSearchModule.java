package com.example.mateuszdziubek.easysearch.usersearch;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class UserSearchModule {

    private UserSearchContract.View userSearchView;
    private UserSearchContract.Repository usersRepository;

    public UserSearchModule(UserSearchContract.View userSearchView) {
        this.userSearchView = userSearchView;
    }

    @Provides
    @Singleton
    public UserSearchContract.Repository provideUsersRepository() {
        return new UsersRepository();
    }

    @Provides
    @Singleton
    public UserSearchContract.View provideUserSearchView() {
        return userSearchView;
    }

    @Provides
    @Singleton
    public UserSearchPresenter userSearchPresenter() {
        return new UserSearchPresenter(userSearchView, usersRepository);
    }
}
