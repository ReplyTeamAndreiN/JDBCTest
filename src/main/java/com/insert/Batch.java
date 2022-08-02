package com.insert;

import java.sql.*;
import java.util.Date;

public class Batch {
    private static final String URL = "jdbc:mysql://localhost:3306/JDBCTest";
    private static final String USERNAME_NAME = "JDBCTestUser";
    private static final String PASSWORD = "root";


    public static void main(String[] args) throws SQLException {
        System.out.println("Start program at " + new Date());
        Connection connection = null;
        //int i = 0;


        try {

            connection = DriverManager.getConnection(URL, USERNAME_NAME, PASSWORD);

            Statement statement = connection.createStatement();

            for (int i = 0; i <= 10000; i++) {
                String sql = "insert into person(id,name) values (" + i + ",'Andrei')";
                statement.addBatch(sql);
            }
            statement.executeBatch();
            statement.close();


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
        }
        System.out.println("End program at " + new Date());
    }

}
