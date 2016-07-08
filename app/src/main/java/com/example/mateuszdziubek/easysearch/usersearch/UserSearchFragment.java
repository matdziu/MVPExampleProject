package com.example.mateuszdziubek.easysearch.usersearch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.mateuszdziubek.easysearch.R;
import com.example.mateuszdziubek.easysearch.usersearch.model.UserModel;
import com.example.mateuszdziubek.easysearch.usersearch.restdownload.GitUsersListProvider;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserSearchFragment extends Fragment implements UserSearchContract.View {

    ListView listView;
    EditText editText;

    List<String> users = new ArrayList<>();
    UserSearchContract.UserActions userSearchPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_user_search, container, false);

        listView = (ListView) root.findViewById(R.id.listView);
        editText = (EditText) root.findViewById(R.id.editText);
        userSearchPresenter = new UserSearchPresenter(this, users, listView, editText);

        userSearchPresenter.startApplication();

        return root;
    }

    @Override
    public void showPopulatedList(final List<String> users, final ListView listView) {
        GitUsersListProvider gitUsersListProvider = new GitUsersListProvider();
        Callback<List<UserModel>> callback = new Callback<List<UserModel>>() {
            @Override
            public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
                Log.d("usersDownload", "success!");

                for(UserModel userModel : response.body()) {
                    users.add(userModel.getLogin());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, users);
                listView.setAdapter(adapter);
                applyDynamicSearch(editText, listView);

            }

            @Override
            public void onFailure(Call<List<UserModel>> call, Throwable t) {
                Log.d("usersDownload", "failure!");
            }
        };

        gitUsersListProvider.downloadUsers(callback);

    }

    @Override
    public void applyDynamicSearch(EditText editText, ListView listView) {
        final ArrayAdapter<String> adapter = (ArrayAdapter<String>) listView.getAdapter();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() >= 3)
                    adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
