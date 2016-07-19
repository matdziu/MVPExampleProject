package com.example.mateuszdziubek.easysearch.usersearch;


import com.example.mateuszdziubek.easysearch.usersearch.model.Items;
import com.example.mateuszdziubek.easysearch.usersearch.model.LocationModel;
import com.example.mateuszdziubek.easysearch.usersearch.model.LocationsCallback;
import com.example.mateuszdziubek.easysearch.usersearch.model.RepositoryCallback;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class LocationSearchPresenter implements LocationSearchContract.UserActions {

    private LocationSearchContract.View locationSearchView;

    private LocationSearchContract.Repository locationsRepository;

    private List<String> locations = new ArrayList<>();

    private LocationSearchContract.CacheProvider cacheProvider;

    public LocationSearchPresenter(LocationSearchContract.View locationSearchView,
                                   LocationSearchContract.Repository locationsRepository,
                                   LocationSearchContract.CacheProvider cacheProvider) {
        this.locationSearchView = locationSearchView;
        this.locationsRepository = locationsRepository;
        this.cacheProvider = cacheProvider;
    }

    @Override
    public void search(final String query) {
        if (query.length() >= 3) {
            RepositoryCallback locationCallback = new LocationsCallback() {
                @Override
                public void onResult(LocationModel result) {
                    if (result != null && result.getItems().length > 0) {
                        locationSearchView.hideProgressBar();
                        locationSearchView.hideNoResultsTextView();
                        for(Items item : result.getItems()) {
                            locations.add(item.getName());
                        }

                        locationSearchView.showPopulatedList(locations);
                    }
                    else {
                        locationSearchView.clearListView();
                        locationSearchView.showNoResultsTextView();
                        locationSearchView.hideProgressBar();
                    }
                }

                @Override
                public void onError(Throwable error) {

                }
            };

            if (locationSearchView.clearCache()) {
                locations.clear();
                locationSearchView.lockCacheClear();
            }

            if (locations.size() == 0) {
                locationSearchView.displayProgressBar();
                locationsRepository.getLocations(query)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<Response<LocationModel>>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                locationCallback.onResult(cacheProvider.getCache(query));
                            }

                            @Override
                            public void onNext(Response<LocationModel> response) {
                                if (response.isSuccessful()) {
                                    //putting data to cache
                                    cacheProvider.setCache(query, response.body());

                                    locationCallback.onResult(response.body());
                                }
                                else {
                                    locationCallback.onResult(cacheProvider.getCache(query));
                                }
                            }
                        });
            }
            else {
                locationSearchView.applyFilter(query);
            }
        }

    }

}
