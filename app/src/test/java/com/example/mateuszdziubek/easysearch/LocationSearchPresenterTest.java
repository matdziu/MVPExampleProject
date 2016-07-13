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

import java.util.Arrays;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class LocationSearchPresenterTest {

    @Mock
    private LocationSearchContract.View locationSearchView;

    @Captor
    private ArgumentCaptor<RepositoryCallback<LocationModel>> repositoryCallbackCaptor;

    @Mock
    LocationSearchContract.Repository locationsRepository;

    @Mock
    LocationModel locationModel;

    @Mock
    LocationModel locationModel1;

    @Mock
    LocationModel locationModel2;

    private LocationSearchPresenter locationSearchPresenter;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        locationSearchPresenter =
                new LocationSearchPresenter(locationSearchView, locationsRepository);
    }


//    @Test
//    public void isSearchingForUserChangingEditText() {
//        String user = "user";
//        locationSearchPresenter.searchForUser(user);
//        verify(locationSearchView).fillEditText(user);
//    }
//
//    @Test
//    public void isSingleUserFetchedProperly() {
//        when(userModel.getLogin()).thenReturn("test");
//
//        userSearchPresenter.search();
//        verify(usersRepository).getUsers(repositoryCallbackCaptor.capture());
//
//        repositoryCallbackCaptor.getValue().onResult(Arrays.asList(userModel));
//        verify(userSearchView).showPopulatedList(Arrays.asList("test"));
//    }
//
//    @Test
//    public void areMultipleUsersFetchedProperly() {
//        when(userModel.getLogin()).thenReturn("test");
//        when(userModel1.getLogin()).thenReturn("test1");
//        when(userModel2.getLogin()).thenReturn("test2");
//
//        userSearchPresenter.search();
//        verify(usersRepository).getUsers(repositoryCallbackCaptor.capture());
//
//        repositoryCallbackCaptor.getValue().onResult(Arrays.asList(userModel, userModel1, userModel2));
//        verify(userSearchView).showPopulatedList(Arrays.asList("test", "test1", "test2"));
//    }
//
//    @Test(expected = NullPointerException.class)
//    public void isNoUsersResultingInError() {
//        userSearchPresenter.search();
//        verify(usersRepository).getUsers(repositoryCallbackCaptor.capture());
//
//        repositoryCallbackCaptor.getValue().onResult(emptyUserModelList);
//    }
//

}
