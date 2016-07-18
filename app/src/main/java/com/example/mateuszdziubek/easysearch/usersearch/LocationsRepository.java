package com.example.mateuszdziubek.easysearch.usersearch;


import com.example.mateuszdziubek.easysearch.usersearch.model.LocationModel;
import com.example.mateuszdziubek.easysearch.usersearch.model.RepositoryCallback;
import com.example.mateuszdziubek.easysearch.usersearch.restdownload.LocationListProvider;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationsRepository implements LocationSearchContract.Repository {

    private LocationListProvider locationListProvider;

    public LocationsRepository(LocationListProvider locationListProvider) {
        this.locationListProvider = locationListProvider;
    }


    @Override
    public void getLocations(final RepositoryCallback<LocationModel> locationCallback, final String query) {
        locationListProvider.downloadLocations(locationCallback, query);
    }

}
