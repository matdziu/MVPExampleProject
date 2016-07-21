package com.example.mateuszdziubek.easysearch;


import com.example.mateuszdziubek.easysearch.usersearch.LocationSearchContract;
import com.example.mateuszdziubek.easysearch.usersearch.model.LocationModel;
import com.example.mateuszdziubek.easysearch.usersearch.restdownload.CacheProvider;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Calendar;
import java.util.Map;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CacheProviderTest {

    @Mock
    Map<String, LocationModel> cacheMap;

    @Mock
    Map<String, Long> cacheTimeMap;

    private LocationSearchContract.CacheProvider cacheProvider;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        cacheProvider = new CacheProvider(cacheMap, cacheTimeMap);

    }

    @Test
    public void isCacheLoadedAtCorrectTime() {
        when(cacheTimeMap.get(anyString())).thenReturn(Calendar.getInstance().getTimeInMillis() - 30000L);
        when(cacheMap.containsKey(anyString())).thenReturn(true);


        cacheProvider.getCache("abc");
        verify(cacheMap).get("abc");
    }
}