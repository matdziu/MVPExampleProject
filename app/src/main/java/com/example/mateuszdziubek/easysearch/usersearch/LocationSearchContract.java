package com.example.mateuszdziubek.easysearch.usersearch;

import com.example.mateuszdziubek.easysearch.usersearch.model.LocationModel;
import com.example.mateuszdziubek.easysearch.usersearch.model.RepositoryCallback;

import java.util.List;


public interface LocationSearchContract {

    interface View {

        void showPopulatedList(List<String> locations);

        void applyFilter(String query);

    }

    interface UserActions {

        void search(String query);

    }

    interface Repository {

        void getLocations(RepositoryCallback<LocationModel> locationCallback, String query);

    }
}
