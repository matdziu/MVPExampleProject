package com.example.mateuszdziubek.easysearch;


import com.example.mateuszdziubek.easysearch.usersearch.LocationSearchContract;
import com.example.mateuszdziubek.easysearch.usersearch.LocationsRepository;
import com.example.mateuszdziubek.easysearch.usersearch.model.LocationModel;
import com.example.mateuszdziubek.easysearch.usersearch.restdownload.ApiProvider;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import retrofit2.Response;
import rx.Observable;
import rx.observers.TestSubscriber;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LocationsRepositoryTest {

    @Mock
    ApiProvider apiProvider;

    @Mock
    LocationModel locationModel;

    @Rule
    public RxSchedulersOverrideRule rxSchedulersOverrideRule = new RxSchedulersOverrideRule();

    private LocationSearchContract.Repository locationsRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        locationsRepository = new LocationsRepository(apiProvider);
    }

    @Test
    public void isRepositoryCallResultingInObservableWithResponse() {
        when(apiProvider.downloadLocations(anyString())).thenReturn(Observable.just(Response.success(locationModel)));

        locationsRepository.getLocations(anyString());
        verify(apiProvider).downloadLocations(anyString());
    }

    @Test
    public void isObservableCarryingCorrectlyOneModel() {
        TestSubscriber<LocationModel> testSubscriber = new TestSubscriber<>();
        when(apiProvider.downloadLocations(anyString())).thenReturn(Observable.just(Response.success(locationModel)));

        Observable<LocationModel> observable = locationsRepository.getLocations(anyString());
        observable.subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(1);

    }
}