package com.example.mateuszdziubek.easysearch;

import com.example.mateuszdziubek.easysearch.usersearch.LocationSearchContract;
import com.example.mateuszdziubek.easysearch.usersearch.LocationSearchPresenter;
import com.example.mateuszdziubek.easysearch.usersearch.model.LocationModel;
import com.example.mateuszdziubek.easysearch.usersearch.model.RepositoryCallback;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;


public class LocationSearchPresenterTest {

    @Mock
    private LocationSearchContract.View locationSearchView;

    @Mock
    LocationSearchContract.Repository locationsRepository;

    private LocationSearchPresenter locationSearchPresenter;

    @Captor
    private ArgumentCaptor<RepositoryCallback<LocationModel>> repositoryCallbackArgumentCaptor;

    @Captor
    private ArgumentCaptor<String> locationArgumentCaptor;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        locationSearchPresenter =
                new LocationSearchPresenter(locationSearchView, locationsRepository);
    }


    @Test
    public void isThreeLetterQueryResultingInAPICall() {
        String location = "abc";
        locationSearchPresenter.search(location);
        verify(locationsRepository).getLocations(repositoryCallbackArgumentCaptor.capture(),
                locationArgumentCaptor.capture());
    }

    @Test
    public void isTwoLetterQueryResultingInNoAPICall() {
        String location = "ab";
        locationSearchPresenter.search(location);
        verify(locationsRepository, never()).getLocations(repositoryCallbackArgumentCaptor.capture(),
                locationArgumentCaptor.capture());
    }


}
