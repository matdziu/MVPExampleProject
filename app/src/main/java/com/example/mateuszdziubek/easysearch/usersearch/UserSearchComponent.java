package com.example.mateuszdziubek.easysearch.usersearch;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = UserSearchModule.class)
public interface UserSearchComponent {

    void inject(UserSearchFragment userSearchFragment);

}
