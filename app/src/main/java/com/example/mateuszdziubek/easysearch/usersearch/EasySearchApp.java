package com.example.mateuszdziubek.easysearch.usersearch;

import android.app.Application;

/**
 * Created by mateuszdziubek on 12/07/16.
 */
public class EasySearchApp extends Application {

    private UserSearchComponent userSearchComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        userSearchComponent = DaggerUserSearchComponent
    }
}
