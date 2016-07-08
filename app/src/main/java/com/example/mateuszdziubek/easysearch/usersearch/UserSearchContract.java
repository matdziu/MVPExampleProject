package com.example.mateuszdziubek.easysearch.usersearch;

import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public interface UserSearchContract {

    interface View {
        void showPopulatedList(List<String> users);
        void applyDynamicSearch(EditText editText, ListView listView);
    }

    interface UserActions {
        void startApplication();
        void searchForUser(EditText editText);
    }
}
