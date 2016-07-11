package com.example.mateuszdziubek.easysearch;

import com.example.mateuszdziubek.easysearch.usersearch.UserSearchContract;
import com.example.mateuszdziubek.easysearch.usersearch.UserSearchPresenter;
import com.example.mateuszdziubek.easysearch.usersearch.UsersRepository;
import com.example.mateuszdziubek.easysearch.usersearch.model.UserModel;
import com.example.mateuszdziubek.easysearch.usersearch.restdownload.GitUsersListProvider;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Callback;

import static org.mockito.Mockito.verify;


public class UserSearchPresenterTest {

    private List<String> users;

    @Mock
    private UserSearchContract.View userSearchView;

    @Mock
    private GitUsersListProvider gitUsersListProvider;

    private UserSearchPresenter userSearchPresenter;

    @Mock
    private Callback<List<UserModel>> callback;

    @Mock
    private UsersRepository usersRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        users = new ArrayList<>(Arrays.asList("abc", "cde", "fgh"));
        userSearchPresenter =
                new UserSearchPresenter(userSearchView, users);
    }

    @Test
    public void isSearchingForUserChangingEditText() {
        String user = "user";
        userSearchPresenter.searchForUser(user);
        verify(userSearchView).fillEditText(user);
    }

//    @Test
//    public void isDataLoadedProperly() {
//        userSearchPresenter.loadData();
//        verify(usersRepository).getUsers(callback);
//
//    }


}
