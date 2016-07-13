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

import java.util.List;

import javax.inject.Inject;


public class LocationSearchFragment extends Fragment implements LocationSearchContract.View {

    private ListView listView;

    private EditText editText;

    private ArrayAdapter<String> adapter;

    @Inject
    LocationSearchContract.UserActions locationSearchPresenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_location_search, container, false);

        listView = (ListView) root.findViewById(R.id.listView);
        editText = (EditText) root.findViewById(R.id.editText);

        LocationSearchComponent locationSearchComponent = DaggerLocationSearchComponent.builder().
                locationSearchModule(new LocationSearchModule(this)).build();

        locationSearchComponent.inject(this);

        applyDynamicSearch();

        return root;
    }

    @Override
    public void showPopulatedList(final List<String> users) {
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, users);
        listView.setAdapter(adapter);

    }

    @Override
    public void applyFilters(String filter) {
        ArrayAdapter<String> adapter = (ArrayAdapter<String>) listView.getAdapter();
        adapter.getFilter().filter(filter);
    }

    public void applyDynamicSearch() {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                locationSearchPresenter.search(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

}
