package org.example;

import org.example.connect.Queries;

import java.util.HashMap;

public class Authorization extends Login {
    private String firstName;
    private String lastName;

    public boolean IsDataTrue() {
        Queries queries = new Queries();
        if (queries.isDataTrue(username, password)) {
            HashMap<String, String> name = queries.getName(username, password);
            firstName = name.get("first_name");
            lastName = name.get("last_name");

            return true;
        }

        return false;
    }

    public Person getPerson() {
        return new Person(username, password, firstName, lastName);
    }
}
