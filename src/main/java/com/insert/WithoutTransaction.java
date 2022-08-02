package com.insert;

import java.sql.*;

public class WithoutTransaction {
    private static final String URL = "jdbc:mysql://localhost:3306/JDBCTest";
    private static final String USERNAME_NAME = "JDBCTestUser";
    private static final String PASSWORD = "root";

    Connection connection = DriverManager.getConnection(URL, USERNAME_NAME, PASSWORD);
    Statement statement = connection.createStatement();
    ;
    ResultSet resultSet = null;

    public WithoutTransaction() throws SQLException {
    }


    public static void main(String[] args) throws SQLException {

        String sql1 = "insert into person(id,name) values (1,'Andrei')";
        String sql2 = "insert into person(id,name) values (2,'Mihai')";
        String sql3 = "insert into person(id,name) values (2,'Valentin')";

        new WithoutTransaction().insertIntoTable(sql1, sql2, sql3);
        new WithoutTransaction().countNumberOfRowsAffected();
    }

    public void insertIntoTable(String sql1, String sql2, String sql3) {


        try {

            statement.executeUpdate(sql1);
            statement.executeUpdate(sql2);
            statement.executeUpdate(sql3);

            statement.close();


        } catch (SQLException e) {

            // throw new RuntimeException(e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int countNumberOfRowsAffected() throws SQLException {
        int rowsComited = 0;
        resultSet = statement.executeQuery("SELECT * FROM person");
            while (resultSet.next()) {
              rowsComited++;
        }
        return rowsComited;
    }
}

