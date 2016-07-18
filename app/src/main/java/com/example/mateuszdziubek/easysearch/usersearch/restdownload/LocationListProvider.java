package com.example.mateuszdziubek.easysearch.usersearch.restdownload;

import com.example.mateuszdziubek.easysearch.usersearch.model.LocationModel;
import com.example.mateuszdziubek.easysearch.usersearch.model.RepositoryCallback;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LocationListProvider {

    private Map<String, LocationModel> cacheMap = new HashMap<>();

    private Map<String, Long> cacheTimeMap = new HashMap<>();

    final String BASE_URL = "http://api.stage.klart.se";

    Retrofit retrofit = new Retrofit.Builder()
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    RestAPI service = retrofit.create(RestAPI.class);

    public void downloadLocations(RepositoryCallback<LocationModel> callback, String query) {
        Observable<LocationModel> locationsStream = service.getLocations(query);

        locationsStream.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LocationModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        //if exists taking data from cache
                        if (cacheMap.containsKey(query) &&
                            (Calendar.getInstance().getTimeInMillis() - cacheTimeMap.get(query).longValue()) <= 60000) {
                        callback.onResult(cacheMap.get(query));
                        }
                        else {
                            callback.onResult(null);
                        }

                    }

                    @Override
                    public void onNext(LocationModel locationModel) {
                        //putting data to cache
                        cacheMap.put(query, locationModel);
                        cacheTimeMap.put(query, Calendar.getInstance().getTimeInMillis());

                        callback.onResult(locationModel);
                    }
                });
    }

}