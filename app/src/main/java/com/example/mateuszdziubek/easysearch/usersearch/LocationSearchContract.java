package com.example.mateuszdziubek.easysearch.usersearch;

import android.location.Location;

import com.example.mateuszdziubek.easysearch.usersearch.model.LocationModel;

import java.util.List;

import retrofit2.Response;
import rx.Observable;


public interface LocationSearchContract {

    interface View {

        void showPopulatedList(List<String> locations);

        void applyFilter(String query);

        boolean clearCache();

        void lockCacheClear();

        void displayProgressBar();

        void hideProgressBar();

        void showNoResultsTextView();

        void hideNoResultsTextView();

        void clearListView();

    }

    interface UserActions {

        void search(String query);

        void unsubscribe();

    }

    interface Repository {

        Observable<Response<LocationModel>> getLocations(String query);

    }

    interface CacheProvider {

        LocationModel getCache(String query);

        void setCache(String query, LocationModel locationModel);
    }
}
