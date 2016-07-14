package com.example.mateuszdziubek.easysearch.usersearch;


import android.util.Log;

import com.example.mateuszdziubek.easysearch.usersearch.model.Items;
import com.example.mateuszdziubek.easysearch.usersearch.model.LocationModel;
import com.example.mateuszdziubek.easysearch.usersearch.model.RepositoryCallback;
import com.example.mateuszdziubek.easysearch.usersearch.model.LocationsCallback;

import java.util.ArrayList;
import java.util.List;


public class LocationSearchPresenter implements LocationSearchContract.UserActions {

    private LocationSearchContract.View locationSearchView;

    private LocationSearchContract.Repository locationsRepository;

    private List<String> locations = new ArrayList<>();

    public LocationSearchPresenter(LocationSearchContract.View locationSearchView,
                                   LocationSearchContract.Repository locationsRepository) {
        this.locationSearchView = locationSearchView;
        this.locationsRepository = locationsRepository;
    }

    @Override
    public void search(final String query) {
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
            locationsRepository.getLocations(locationCallback, query);
        }
        else {
            locationSearchView.applyFilter(query);
        }

    }

}
