package com.example.mateuszdziubek.easysearch;

import com.example.mateuszdziubek.easysearch.usersearch.LocationSearchContract;
import com.example.mateuszdziubek.easysearch.usersearch.LocationsRepository;
import com.example.mateuszdziubek.easysearch.usersearch.model.LocationModel;
import com.example.mateuszdziubek.easysearch.usersearch.model.RepositoryCallback;
import com.example.mateuszdziubek.easysearch.usersearch.restdownload.LocationListProvider;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;


public class LocationsRepositoryTest {

    private LocationSearchContract.Repository locationsRepository;

    @Captor
    private ArgumentCaptor<RepositoryCallback<LocationModel>> repositoryCallbackArgumentCaptor;

    @Captor
    private ArgumentCaptor<String> locationArgumentCaptor;

    @Captor
    private ArgumentCaptor<Callback<LocationModel>> retrofitCallbackCaptor;

    @Captor
    private ArgumentCaptor<Call<LocationModel>> callCaptor;

    @Captor
    private ArgumentCaptor<Response<LocationModel>> responseCaptor;

    @Mock
    private LocationListProvider locationProvider;

    @Mock
    private RepositoryCallback<LocationModel> repositoryCallback;

    @Mock
    private Callback retrofitCallback;

    @Mock
    private Call<LocationModel> call;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        locationsRepository = new LocationsRepository(locationProvider);
    }

    @Test
    public void isFailureInAPICallResultingInCacheLoading() {
//        when(response.isSuccessful()).thenReturn(false);

        locationsRepository.getLocations(repositoryCallbackArgumentCaptor.capture(), anyString());

//        doAnswer(new Answer() {
//            @Override
//            public Object answer(InvocationOnMock invocation) throws Throwable {
////                verify(locationProvider).downloadLocations(retrofitCallbackCaptor.capture(),
////                        locationArgumentCaptor.capture());
//
//                retrofitCallbackCaptor.getValue().onResponse();
//                return null;
//            }
//        }).when(locationProvider).downloadLocations(retrofitCallbackCaptor.capture(), anyString());

//        verify(locationProvider).downloadLocations(retrofitCallbackCaptor.capture(), anyString());
        retrofitCallbackCaptor.getValue().onResponse(call, Response.<LocationModel>error(404, ResponseBody.create(MediaType.parse(""), "")));

        repositoryCallbackArgumentCaptor.getValue().onResult(null);
    }

}