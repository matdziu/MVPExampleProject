package com.example.mateuszdziubek.easysearch.usersearch.restdownload;

import com.example.mateuszdziubek.easysearch.usersearch.model.LocationModel;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

public class ApiProvider {

    final String BASE_URL = "http://api.stage.klart.se";

    Retrofit retrofit = new Retrofit.Builder()
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    RestAPI service = retrofit.create(RestAPI.class);

    public Observable<Response<LocationModel>> downloadLocations(String query) {
        return service.getLocations(query);
    }

}