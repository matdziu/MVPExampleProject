package com.example.mateuszdziubek.easysearch.usersearch;

import com.example.mateuszdziubek.easysearch.usersearch.restdownload.ApiProvider;
import com.example.mateuszdziubek.easysearch.usersearch.restdownload.CacheProvider;

import java.util.HashMap;

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
    public LocationSearchContract.Repository provideUsersRepository(ApiProvider apiProvider, LocationSearchContract.CacheProvider cacheProvider) {
        return new LocationsRepository(apiProvider, cacheProvider);
    }

    @Provides
    @Singleton
    public LocationSearchContract.View provideUserSearchView() {
        return userSearchView;
    }

    @Provides
    @Singleton
    public ApiProvider provideLocationListProvider() {
        return new ApiProvider();
    }

    @Provides
    @Singleton
    public LocationSearchContract.CacheProvider provideCacheProvider() {
        return new CacheProvider(new HashMap<>(), new HashMap<>());
    }
}
