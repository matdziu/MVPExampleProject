package com.example.mateuszdziubek.easysearch.usersearch.restdownload;

import com.example.mateuszdziubek.easysearch.usersearch.model.LocationModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface RestAPI {

    @GET("/search/{location}")
    Call<LocationModel> getLocations(@Path("location") String location);
}
