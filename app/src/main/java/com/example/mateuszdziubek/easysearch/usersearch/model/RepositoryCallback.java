package com.example.mateuszdziubek.easysearch.usersearch.model;


public interface RepositoryCallback<T> {

    void onResult(T result);

    void onError(Throwable error);
}
