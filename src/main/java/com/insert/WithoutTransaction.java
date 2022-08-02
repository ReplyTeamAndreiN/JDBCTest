package com.insert;

import java.sql.*;

public class WithoutTransaction {
    private static final String URL = "jdbc:mysql://localhost:3306/JDBCTest";
    private static final String USERNAME_NAME = "JDBCTestUser";
    private static final String PASSWORD = "root";
    Connection connection = null;
    private ResultSet resultSet;

    public static void main(String[] args) {

        String sql1 = "insert into person(id,name) values (1,'Andrei')";
        String sql2 = "insert into person(id,name) values (2,'Mihai')";
        String sql3 = "insert into person(id,name) values (2,'Valentin')";

        new WithoutTransaction().countNumberOfRowsAffected(sql1, sql2, sql3);
    }

    public int countNumberOfRowsAffected(String sql1, String sql2, String sql3) {
        int rowsComited = 0;
        try {
            connection = DriverManager.getConnection(URL, USERNAME_NAME, PASSWORD);

            Statement statement = connection.createStatement();

            statement.executeUpdate(sql1);
            resultSet = statement.getResultSet();
            statement.executeUpdate(sql2);
            resultSet = statement.getResultSet();
            statement.executeUpdate(sql3);
            resultSet = statement.getResultSet();
            statement.close();

            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            rowsComited = resultSetMetaData.getColumnCount();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return rowsComited;
        }
    }
}
