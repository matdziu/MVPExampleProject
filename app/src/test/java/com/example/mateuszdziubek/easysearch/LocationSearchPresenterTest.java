package com.example.mateuszdziubek.easysearch;

import com.example.mateuszdziubek.easysearch.usersearch.LocationSearchContract;
import com.example.mateuszdziubek.easysearch.usersearch.LocationSearchPresenter;
import com.example.mateuszdziubek.easysearch.usersearch.model.LocationModel;
import com.example.mateuszdziubek.easysearch.usersearch.model.RepositoryCallback;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rx.Observable;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class LocationSearchPresenterTest {

    @Mock
    private LocationSearchContract.View locationSearchView;

    @Mock
    LocationSearchContract.Repository locationsRepository;

    @Mock
    LocationSearchContract.CacheProvider cacheProvider;

    @Mock
    RepositoryCallback<LocationModel> repositoryCallback;

    @Mock
    LocationModel locationModel;

    private LocationSearchPresenter locationSearchPresenter;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        locationSearchPresenter =
                new LocationSearchPresenter(locationSearchView, locationsRepository);
    }


    @Test
    public void isThreeLetterQueryResultingInAPICall() {
        when(locationsRepository.getLocationsOnline(anyString())).thenReturn(Observable.just(locationModel));

        locationSearchPresenter.search("abc");
        verify(locationsRepository).getLocationsOnline("abc");
    }

    @Test
    public void isTwoLetterQueryResultingInNoAPICall() {
        String location = "ab";
        locationSearchPresenter.search(location);
        verify(locationsRepository, never()).getLocationsOnline(eq(location));
    }



}