package com.example.mateuszdziubek.easysearch.usersearch;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mateuszdziubek.easysearch.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.inject.Inject;


public class LocationSearchFragment extends Fragment implements LocationSearchContract.View {

    private ListView listView;

    private EditText editText;

    private ArrayAdapter<String> adapter;

    private String previousQuery = "";

    private boolean enableNewApiCall = false;

    private ProgressBar progressBar;

    private TextView textView;

    private List<String> recentSearches = new ArrayList<>();

    private TextView recentTextView;

    private ArrayAdapter<String> recentSearchesAdapter;


    @Inject
    LocationSearchContract.UserActions locationSearchPresenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_location_search, container, false);

        listView = (ListView) root.findViewById(R.id.listView);
        editText = (EditText) root.findViewById(R.id.editText);
        textView = (TextView) root.findViewById(R.id.textView);

        recentTextView = (TextView) root.findViewById(R.id.recentTextView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getAdapter().getItem(i).toString();

                Toast.makeText(getContext(), item, Toast.LENGTH_SHORT).show();

                if (!recentSearches.contains(item)) {
                    recentSearches.add(item);
                }
            }
        });

        progressBar = (ProgressBar) root.findViewById(R.id.progressBar);
        hideProgressBar();

        LocationSearchComponent locationSearchComponent = DaggerLocationSearchComponent.builder().
                locationSearchModule(new LocationSearchModule(this)).build();

        locationSearchComponent.inject(this);

        applyDynamicSearch();


        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("PREF", Context.MODE_PRIVATE);

        recentSearches = new ArrayList<>(sharedPreferences.getStringSet("recentSearches", new HashSet<String>()));

        recentSearchesAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_list_item_1, recentSearches);

        listView.setAdapter(recentSearchesAdapter);



        return root;
    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("PREF", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet("recentSearches", new HashSet<>(recentSearches));
        editor.commit();
    }

    @Override
    public void showPopulatedList(final List<String> locations) {
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, locations);
        listView.setAdapter(adapter);

    }

    @Override
    public void applyFilter(String query) {
        adapter.getFilter().filter(query, new Filter.FilterListener() {
            @Override
            public void onFilterComplete(int i) {
                if(i == 0) {
                    showNoResultsTextView();
                }
                else {
                    hideNoResultsTextView();
                }
            }
        });

    }

    @Override
    public boolean clearCache() {
        return enableNewApiCall;
    }

    @Override
    public void lockCacheClear() {
        enableNewApiCall = false;
    }

    @Override
    public void displayProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showNoResultsTextView() {
        textView.setText("no results found");
    }

    @Override
    public void hideNoResultsTextView() {
        textView.setText("");
    }

    @Override
    public void clearListView() {
        if (adapter != null) {
            adapter.clear();
            adapter.notifyDataSetChanged();
        }
    }

    public void applyDynamicSearch() {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 3) {
                    previousQuery = charSequence.toString();
                }

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                locationSearchPresenter.search(charSequence.toString());

                if (charSequence.length() == 0) {
                    recentSearchesAdapter = new ArrayAdapter<>(getContext(),
                            android.R.layout.simple_list_item_1, recentSearches);
                    listView.setAdapter(recentSearchesAdapter);
                    recentTextView.setVisibility(View.VISIBLE);
                    hideNoResultsTextView();

                }
                else {
                    recentTextView.setVisibility(View.GONE);
                    listView.setAdapter(adapter);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() == 3 && editable.toString().trim().length() != 0) {
                    if (!previousQuery.equals(editable.toString()) && !previousQuery.isEmpty()) {
                        enableNewApiCall = true;
                        locationSearchPresenter.search(editable.toString());
                    }
                }

            }

        });

    }

    @Override
    public void onStop() {
        super.onStop();
        locationSearchPresenter.unsubscribe();
    }
}
