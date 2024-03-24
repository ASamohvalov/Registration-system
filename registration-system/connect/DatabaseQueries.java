package org.example.connect;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseQueries {
    private static final String url = "jdbc:postgresql://localhost/test";
    private static final String user = "postgres";
    private static final String password = "pass";

    private String sql;

    public DatabaseQueries(String sql) {
        this.sql = sql;
    }

    private Connection connect() throws SQLException, ClassNotFoundException{
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(url, user, password);
    }

    public void setData() {
        try (Connection connection = connect()) {
            Statement statement = connection.createStatement();
            statement.execute(sql);
            statement.close();

        } catch (SQLException e) {
            System.out.println("sql error");
        } catch (ClassNotFoundException e) {
            System.out.println("class not found error");
        }
    }

    public HashMap<String, String> getData(ArrayList<String> data) {
        HashMap<String, String> result = new HashMap<>();
        try (Connection connection = connect()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()) {
                for (String columName : data) {
                    result.put(columName, rs.getString(columName));
                }
            }

            rs.close();
            statement.close();

        } catch (SQLException e) {
            System.out.println("sql error, " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("class not found error, " + e.getMessage());
        }
        return result;
    }

    public boolean isDataExist(ArrayList<String> data) {
        boolean result = false;
        try (Connection connection = connect()){
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {
                result = true;
            }

            statement.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("sql error, " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("class not found error, " + e.getMessage());
        }
        return result;
    }
}
