package com.example.mateuszdziubek.easysearch.usersearch;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = LocationSearchModule.class)
public interface LocationSearchComponent {

    void inject(LocationSearchFragment locationSearchFragment);

}
