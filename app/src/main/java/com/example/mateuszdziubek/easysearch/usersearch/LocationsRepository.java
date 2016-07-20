package com.example.mateuszdziubek.easysearch.usersearch;


import com.example.mateuszdziubek.easysearch.usersearch.model.LocationModel;
import com.example.mateuszdziubek.easysearch.usersearch.restdownload.ApiProvider;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LocationsRepository implements LocationSearchContract.Repository {

    private ApiProvider apiProvider;

    public LocationsRepository(ApiProvider apiProvider) {
        this.apiProvider = apiProvider;
    }


    @Override
    public Observable<LocationModel> getLocations(String query) {
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

}