package com.example.mateuszdziubek.easysearch.usersearch.restdownload;

import com.example.mateuszdziubek.easysearch.usersearch.model.LocationModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LocationListProvider {

    final String BASE_URL = "http://api.stage.klart.se";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    RestAPI service = retrofit.create(RestAPI.class);

    public void downloadLocations(Callback<LocationModel> callback, String query) {
        Call<LocationModel> call = service.getLocations(query);
        call.enqueue(callback);

//        try {
//            callback.onResponse(call, call.execute());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

}
