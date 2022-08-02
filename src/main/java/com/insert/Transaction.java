package com.insert;

import java.sql.*;

public class Transaction {
    private static final String URL = "jdbc:mysql://localhost:3306/JDBCTest";
    private static final String USERNAME_NAME = "JDBCTestUser";
    private static final String PASSWORD = "root";

    public Connection createConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USERNAME_NAME, PASSWORD);
        connection.setAutoCommit(false);
        return connection;
    }
    Connection connection = createConnection();
    Statement statement = connection.createStatement();;
    ResultSet resultSet = null;

    public Transaction() throws SQLException {
    }


    public static void main(String[] args) throws SQLException {

        String sql1 = "insert into person(id,name) values (1,'Andrei')";
        String sql2 = "insert into person(id,name) values (2,'Mihai')";
        String sql3 = "insert into person(id,name) values (2,'Valentin')";

        new Transaction().insertIntoTable(sql1, sql2, sql3);
        new Transaction().countNumberOfRowsAffected();
    }

    public void insertIntoTable(String sql1, String sql2, String sql3) {

        try {

            statement.executeUpdate(sql1);
            statement.executeUpdate(sql2);
            statement.executeUpdate(sql3);

            statement.close();

            connection.commit();

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
