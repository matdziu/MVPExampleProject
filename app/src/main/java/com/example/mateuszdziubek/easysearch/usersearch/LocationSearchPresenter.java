package com.example.mateuszdziubek.easysearch.usersearch;


import com.example.mateuszdziubek.easysearch.usersearch.model.Items;
import com.example.mateuszdziubek.easysearch.usersearch.model.LocationModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;
import rx.Subscription;
import rx.functions.Action1;


public class LocationSearchPresenter implements LocationSearchContract.UserActions {

    private LocationSearchContract.View locationSearchView;

    private LocationSearchContract.Repository locationsRepository;

    private LocationSearchContract.CacheProvider cacheProvider;

    private List<String> locations = new ArrayList<>();

    private Subscription subscription;

    private Action1<Response<LocationModel>> onNextAction;

    private Action1<Throwable> onErrorAction;

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

                defineObserverActions(query);
                subscription = locationsRepository.getLocations(query)
                        .subscribe(onNextAction, onErrorAction);
            }
            else {
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

    private void defineObserverActions(String query) {
        onNextAction = new Action1<Response<LocationModel>>() {
            @Override
            public void call(Response<LocationModel> response) {
                if (response.isSuccessful()) {
                    //putting data to cache
                    cacheProvider.setCache(query, response.body());

                    handleResult(response.body());
                } else {
                    handleResult(cacheProvider.getCache(query));
                }
            }
        };

        onErrorAction = new Action1<Throwable>() {
            @Override
            public void call(Throwable e) {
                handleResult(cacheProvider.getCache(query));
        }
    };
    }

}