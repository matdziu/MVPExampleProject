package com.example.mateuszdziubek.easysearch.usersearch;


import com.example.mateuszdziubek.easysearch.usersearch.model.LocationModel;
import com.example.mateuszdziubek.easysearch.usersearch.model.RepositoryCallback;
import com.example.mateuszdziubek.easysearch.usersearch.restdownload.LocationListProvider;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationsRepository implements LocationSearchContract.Repository {

    @Override
    public void getLocations(final RepositoryCallback<LocationModel> locationCallback, String query) {
        Callback<LocationModel> retrofitCallback = new Callback<LocationModel>() {
            @Override
            public void onResponse(Call<LocationModel> call, Response<LocationModel> response) {
//                Log.d("locationDownload", "success!");
                locationCallback.onResult(response.body());
            }

            @Override
            public void onFailure(Call<LocationModel> call, Throwable t) {
//                Log.d("locationDownload", "failure!");
                locationCallback.onError(new Exception());
            }
        };

        LocationListProvider locationListProvider = new LocationListProvider();
        locationListProvider.downloadLocations(retrofitCallback, query);
    }
}
