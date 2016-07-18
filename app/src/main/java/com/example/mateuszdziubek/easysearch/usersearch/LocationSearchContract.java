package com.example.mateuszdziubek.easysearch.usersearch;

import com.example.mateuszdziubek.easysearch.usersearch.model.LocationModel;
import com.example.mateuszdziubek.easysearch.usersearch.model.RepositoryCallback;

import java.util.List;
import java.util.Map;


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

    }

    interface Repository {

        void getLocations(RepositoryCallback<LocationModel> locationCallback, String query);

    }
}
