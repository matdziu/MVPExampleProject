package com.example.mateuszdziubek.easysearch.usersearch;

import java.util.List;

public interface UserSearchContract {

    interface View {
        void showPopulatedList(List<String> users);
        void fillEditText(String text);
    }

    interface UserActions {
        void loadData();
        void searchForUser(String user);
    }
}
