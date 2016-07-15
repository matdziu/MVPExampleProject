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

    private Map<String, LocationModel> cacheMap = new HashMap<>();

    private Map<String, Long> cacheTimeMap = new HashMap<>();

    private LocationListProvider locationListProvider;

    public LocationsRepository(LocationListProvider locationListProvider) {
        this.locationListProvider = locationListProvider;
    }


    @Override
    public void getLocations(final RepositoryCallback<LocationModel> locationCallback, final String query) {
        final Callback<LocationModel> retrofitCallback = new Callback<LocationModel>() {
            @Override
            public void onResponse(Call<LocationModel> call, Response<LocationModel> response) {
//                Log.d("locationDownload", "success!");
                if (response.isSuccessful()) {
                    locationCallback.onResult(response.body());

                    cacheMap.put(query, response.body());
                    cacheTimeMap.put(query, Calendar.getInstance().getTimeInMillis());

                }
//                else if (cacheMap.containsKey(query) &&
//                        (Calendar.getInstance().getTimeInMillis() - cacheTimeMap.get(query).longValue()) <= 60000) {
//                    locationCallback.onResult(cacheMap.get(query));
//                }
                else {
                    locationCallback.onResult(null);
                }

            }

            @Override
            public void onFailure(Call<LocationModel> call, Throwable t) {
//                Log.d("locationDownload", "failure!");

                if (cacheMap.containsKey(query) &&
                        (Calendar.getInstance().getTimeInMillis() - cacheTimeMap.get(query).longValue()) <= 60000) {
                    locationCallback.onResult(cacheMap.get(query));
                }
                else {
                    locationCallback.onResult(null);
                }
            }
        };

        locationListProvider.downloadLocations(retrofitCallback, query);
    }

    @Override
    public Map<String, LocationModel> getCacheMap() {
        return cacheMap;
    }
}
