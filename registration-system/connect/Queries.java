package org.example.connect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Queries {
    public void addNewUser(String username, String password, String firstName, String lastName) {
        String sql = "INSERT INTO users (username, password, first_name, last_name)" +
                " VALUES ('" + username + "', '" + password + "', '" + firstName + "', '" + lastName + "')";
        DatabaseQueries queries = new DatabaseQueries(sql);
        queries.setData();
    }

    public boolean isDataTrue(String username, String password) {
        String sql = "SELECT username, password FROM users WHERE username = '" + username + "' AND password = '" + password + "'";
        DatabaseQueries queries = new DatabaseQueries(sql);

        ArrayList<String> data = new ArrayList<>(Arrays.asList("username", "password"));
        return queries.isDataExist(data);
    }

    public HashMap<String, String> getUserData(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'";
        DatabaseQueries queries = new DatabaseQueries(sql);

        ArrayList<String> data = new ArrayList<>(Arrays.asList("username", "password", "first_name", "last_name"));

        return queries.getData(data);
    }

    public boolean isUsernameExist(String username) {
        String sql = "SELECT username FROM users WHERE username = '" + username + "'";
        DatabaseQueries queries = new DatabaseQueries(sql);

        ArrayList<String> data = new ArrayList<>();
        data.add("username");
        return queries.isDataExist(data);
    }

    public HashMap<String, String> getName(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'";
        DatabaseQueries queries = new DatabaseQueries(sql);

        ArrayList<String> data = new ArrayList<>(Arrays.asList("first_name", "last_name"));
        return queries.getData(data);
    }
}
