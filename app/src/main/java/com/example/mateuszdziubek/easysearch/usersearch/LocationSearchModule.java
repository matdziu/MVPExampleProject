package com.example.mateuszdziubek.easysearch.usersearch;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class LocationSearchModule {

    private LocationSearchContract.View userSearchView;

    public LocationSearchModule(LocationSearchContract.View userSearchView) {
        this.userSearchView = userSearchView;
    }

    @Provides
    @Singleton
    public LocationSearchContract.UserActions userSearchPresenter(LocationSearchContract.View view, LocationSearchContract.Repository repository) {
        return new LocationSearchPresenter(view, repository);
    }

    @Provides
    @Singleton
    public LocationSearchContract.Repository provideUsersRepository() {
        return new LocationsRepository();
    }

    @Provides
    @Singleton
    public LocationSearchContract.View provideUserSearchView() {
        return userSearchView;
    }
}
