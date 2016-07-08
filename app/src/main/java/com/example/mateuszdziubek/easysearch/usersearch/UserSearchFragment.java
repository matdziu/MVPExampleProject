package com.example.mateuszdziubek.easysearch.usersearch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.mateuszdziubek.easysearch.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserSearchFragment extends Fragment implements UserSearchContract.View {

    ListView listView;
    EditText editText;

    List<String> users = new ArrayList<>(Arrays.asList("Mateusz", "Daniel", "Robert", "Michał"));
    UserSearchContract.UserActions userSearchPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_user_search, container, false);

        listView = (ListView) root.findViewById(R.id.listView);
        editText = (EditText) root.findViewById(R.id.editText);
        userSearchPresenter = new UserSearchPresenter(this, users, listView);

        userSearchPresenter.startApplication();
        userSearchPresenter.searchForUser(editText);

        return root;
    }

    @Override
    public void showPopulatedList(List<String> users) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, users);
        listView.setAdapter(adapter);
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
                    adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
