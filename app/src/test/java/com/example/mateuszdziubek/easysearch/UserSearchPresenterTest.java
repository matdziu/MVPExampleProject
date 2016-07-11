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

    private UserSearchPresenter userSearchPresenter;

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
    public void isDataFetchedProperly() {
        when(userModel.getLogin()).thenReturn("test");

        userSearchPresenter.loadData();
        verify(usersRepository).getUsers(repositoryCallbackCaptor.capture());

        repositoryCallbackCaptor.getValue().onResult(Arrays.asList(userModel));
        verify(userSearchView).showPopulatedList(Arrays.asList("test"));
    }


}
