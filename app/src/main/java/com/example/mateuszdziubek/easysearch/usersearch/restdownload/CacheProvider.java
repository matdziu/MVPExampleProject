package com.example.mateuszdziubek.easysearch.usersearch.restdownload;


import com.example.mateuszdziubek.easysearch.usersearch.LocationSearchContract;
import com.example.mateuszdziubek.easysearch.usersearch.model.LocationModel;

import java.util.Calendar;
import java.util.Map;

public class CacheProvider implements LocationSearchContract.CacheProvider {

    private Map<String, LocationModel> cacheMap;

    private Map<String, Long> cacheTimeMap;

    public CacheProvider(Map<String, LocationModel> cacheMap, Map<String, Long> cacheTimeMap) {
        this.cacheMap = cacheMap;
        this.cacheTimeMap = cacheTimeMap;
    }

    @Override
    public LocationModel getCache(String query) {
        if (cacheMap.containsKey(query) &&
                (Calendar.getInstance().getTimeInMillis() - cacheTimeMap.get(query).longValue()) <= 60000) {
            return cacheMap.get(query);
        }
        else {
            return null;
        }
    }

    @Override
    public void setCache(String query, LocationModel locationModel) {
        cacheMap.put(query, locationModel);
        cacheTimeMap.put(query, Calendar.getInstance().getTimeInMillis());
    }
}
