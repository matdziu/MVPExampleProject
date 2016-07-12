package com.example.mateuszdziubek.easysearch.usersearch;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class UserSearchModule {

    private UserSearchContract.View userSearchView;

    public UserSearchModule(UserSearchContract.View userSearchView) {
        this.userSearchView = userSearchView;
    }

    @Provides
    @Singleton
    public UserSearchContract.UserActions userSearchPresenter(UserSearchContract.View view, UserSearchContract.Repository repository) {
        return new UserSearchPresenter(view, repository);
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
}
