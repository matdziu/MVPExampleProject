package com.example.mateuszdziubek.easysearch.usersearch.restdownload;

import com.example.mateuszdziubek.easysearch.usersearch.model.LocationModel;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;


public interface RestAPI {

    @GET("/search/{location}")
    Observable<Response<LocationModel>> getLocations(@Path("location") String location);
}
