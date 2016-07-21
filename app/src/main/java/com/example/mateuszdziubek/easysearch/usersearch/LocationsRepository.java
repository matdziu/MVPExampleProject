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
    public Observable<LocationModel> getLocations(String query) {
        Observable<LocationModel> cache = cacheProvider.getCache(query);
        Observable<LocationModel> networkWithSave = apiProvider.downloadLocations(query)
                .doOnNext(locationModel -> cacheProvider.setCache(query, locationModel));

        return Observable.concat(cache, networkWithSave).first()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());

    }

}