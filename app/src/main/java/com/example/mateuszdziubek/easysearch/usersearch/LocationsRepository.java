package com.example.mateuszdziubek.easysearch.usersearch;


import com.example.mateuszdziubek.easysearch.usersearch.model.LocationModel;
import com.example.mateuszdziubek.easysearch.usersearch.restdownload.ApiProvider;

import retrofit2.Response;
import rx.Observable;

public class LocationsRepository implements LocationSearchContract.Repository {

    private ApiProvider apiProvider;

    public LocationsRepository(ApiProvider apiProvider) {
        this.apiProvider = apiProvider;
    }


    @Override
    public Observable<Response<LocationModel>> getLocations(String query) {
        return apiProvider.downloadLocations(query);
    }

}
