package com.example.mateuszdziubek.easysearch.usersearch;

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

                if (locations.size() == 0) {
                    if (result.getItems().length > 0) {
                        for(Items item : result.getItems()) {
                            locations.add(item.getName());
                        }

                        locationSearchView.showPopulatedList(locations);
                    }
                    else {
                        throw new NullPointerException("no data in results");
                    }
                }
                else {
                    locationSearchView.applyFilter(query);
                }
            }

            @Override
            public void onError(Throwable error) {

            }
        };

        locationsRepository.getLocations(locationCallback, query);

    }

}
