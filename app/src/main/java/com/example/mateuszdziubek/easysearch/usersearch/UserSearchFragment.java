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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.mateuszdziubek.easysearch.R;

import java.util.ArrayList;
import java.util.List;


public class UserSearchFragment extends Fragment implements UserSearchContract.View {

    private ListView listView;

    private EditText editText;

    private Button loadUsersButton;

    private List<String> users = new ArrayList<>();

    private UserSearchContract.UserActions userSearchPresenter;

    private UserSearchContract.Repository userSearchRepository;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_user_search, container, false);

        listView = (ListView) root.findViewById(R.id.listView);
        editText = (EditText) root.findViewById(R.id.editText);
        loadUsersButton = (Button) root.findViewById(R.id.loadUsersButton);
        userSearchRepository = new UsersRepository();

        userSearchPresenter = new UserSearchPresenter(this, userSearchRepository);

        loadUsersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userSearchPresenter.loadData();
                loadUsersButton.setVisibility(View.GONE);
            }
        });

        return root;
    }

    @Override
    public void showPopulatedList(final List<String> users) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, users);
        listView.setAdapter(adapter);
        applyDynamicSearch(editText, listView);

    }

    @Override
    public void fillEditText(String text) {
        editText.setText(text);
    }

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
