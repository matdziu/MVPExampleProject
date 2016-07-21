package com.example.mateuszdziubek.easysearch.usersearch;


import com.example.mateuszdziubek.easysearch.usersearch.model.LocationModel;
import com.example.mateuszdziubek.easysearch.usersearch.restdownload.ApiProvider;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LocationsRepository implements LocationSearchContract.Repository {

    private ApiProvider apiProvider;

    private LocationSearchContract.CacheProvider cacheProvider;

    public LocationsRepository(ApiProvider apiProvider, LocationSearchContract.CacheProvider cacheProvider) {
        this.apiProvider = apiProvider;
        this.cacheProvider = cacheProvider;
    }


    @Override
    public Observable<LocationModel> getLocationsOnline(String query) {
        return apiProvider.downloadLocations(query)
                .subscribeOn(Schedulers.newThread())
                .flatMap(response -> {
                    if(response.isSuccessful()) {
                        return Observable.just(response.body());
                    } else {
                        return Observable.error(new Exception());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public LocationModel getLocationsOffline(String query) {
        return cacheProvider.getCache(query);
    }

    @Override
    public void setLocationsOffline(String query, LocationModel locationModel) {
        cacheProvider.setCache(query, locationModel);
    }

}