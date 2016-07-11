package com.example.mateuszdziubek.easysearch;

import com.example.mateuszdziubek.easysearch.usersearch.UserSearchContract;
import com.example.mateuszdziubek.easysearch.usersearch.UserSearchPresenter;
import com.example.mateuszdziubek.easysearch.usersearch.model.RepositoryCallback;
import com.example.mateuszdziubek.easysearch.usersearch.model.UserModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class UserSearchPresenterTest {

    @Mock
    private UserSearchContract.View userSearchView;

    @Captor
    private ArgumentCaptor<RepositoryCallback<List<UserModel>>> repositoryCallbackCaptor;

    @Mock
    UserSearchContract.Repository usersRepository;

    @Mock
    UserModel userModel;

    @Mock
    UserModel userModel1;

    @Mock
    UserModel userModel2;

    private UserSearchPresenter userSearchPresenter;

    private List<UserModel> emptyUserModelList = new ArrayList<>();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userSearchPresenter =
                new UserSearchPresenter(userSearchView, usersRepository);
    }

    @Test
    public void isSearchingForUserChangingEditText() {
        String user = "user";
        userSearchPresenter.searchForUser(user);
        verify(userSearchView).fillEditText(user);
    }

    @Test
    public void isSingleUserFetchedProperly() {
        when(userModel.getLogin()).thenReturn("test");

        userSearchPresenter.loadData();
        verify(usersRepository).getUsers(repositoryCallbackCaptor.capture());

        repositoryCallbackCaptor.getValue().onResult(Arrays.asList(userModel));
        verify(userSearchView).showPopulatedList(Arrays.asList("test"));
    }

    @Test
    public void areMultipleUsersFetchedProperly() {
        when(userModel.getLogin()).thenReturn("test");
        when(userModel1.getLogin()).thenReturn("test1");
        when(userModel2.getLogin()).thenReturn("test2");

        userSearchPresenter.loadData();
        verify(usersRepository).getUsers(repositoryCallbackCaptor.capture());

        repositoryCallbackCaptor.getValue().onResult(Arrays.asList(userModel, userModel1, userModel2));
        verify(userSearchView).showPopulatedList(Arrays.asList("test", "test1", "test2"));
    }

    @Test(expected=NullPointerException.class)
    public void isNoUsersResultingInError() {
        userSearchPresenter.loadData();
        verify(usersRepository).getUsers(repositoryCallbackCaptor.capture());

        repositoryCallbackCaptor.getValue().onResult(emptyUserModelList);
        verify(userSearchPresenter).throwException();
    }


}
