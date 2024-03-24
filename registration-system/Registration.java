package org.example;

import org.example.connect.Queries;

public class Registration extends Login {
    public boolean isUsernameExist(String username) {
        Queries queries = new Queries();
        if (queries.isUsernameExist(username)) {
            return true;
        }

        setUsername(username);
        return false;
    }

    public boolean isPasswordValid(@org.jetbrains.annotations.NotNull String password) {
        if (password.length() < 8) {
            return false;
        }
        setPassword(password);
        return true;
    }
}
