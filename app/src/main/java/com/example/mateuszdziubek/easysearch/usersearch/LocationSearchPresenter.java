package com.example.mateuszdziubek.easysearch.usersearch;


import com.example.mateuszdziubek.easysearch.usersearch.model.Items;
import com.example.mateuszdziubek.easysearch.usersearch.model.LocationModel;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;


public class LocationSearchPresenter implements LocationSearchContract.UserActions {

    private LocationSearchContract.View locationSearchView;

    private LocationSearchContract.Repository locationsRepository;

    private LocationSearchContract.CacheProvider cacheProvider;

    private List<String> locations = new ArrayList<>();

    private Subscription subscription;

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
            if (locationSearchView.clearCache()) {
                locations.clear();
                locationSearchView.lockCacheClear();
            }

            if (locations.size() == 0) {
                locationSearchView.displayProgressBar();

                subscription = locationsRepository.getLocations(query)
                        .subscribe(
                                locationModel -> {
                                    //putting data to cache
                                    cacheProvider.setCache(query, locationModel);
                                    handleResult(locationModel);

                                },

                                error -> {
                                    handleResult(cacheProvider.getCache(query));
                                }
                        );
            } else {
                locationSearchView.applyFilter(query);
            }
        }

    }

    @Override
    public void unsubscribe() {
        subscription.unsubscribe();
    }

    private void handleResult(LocationModel result) {
        if (result != null && result.getItems().length > 0) {
            locationSearchView.hideProgressBar();
            locationSearchView.hideNoResultsTextView();

            for (Items item : result.getItems()) {
                locations.add(item.getName());
            }

            locationSearchView.showPopulatedList(locations);
        } else {
            locationSearchView.clearListView();
            locationSearchView.showNoResultsTextView();
            locationSearchView.hideProgressBar();
        }
    }

}