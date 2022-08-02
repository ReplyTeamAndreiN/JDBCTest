package com.insert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

public class PreparedStatement {
    private static final String URL = "jdbc:mysql://localhost:3306/JDBCTest";
    private static final String USERNAME_NAME = "JDBCTestUser";
    private static final String PASSWORD = "root";


    public static void main(String[] args) throws SQLException {
        System.out.println("Start program at " + new Date());
        Connection connection = null;
        String sql = "insert into person(id,name) values (?,?)";

        try {

            connection = DriverManager.getConnection(URL, USERNAME_NAME, PASSWORD);

            for (int i = 0; i <= 10000; i++) {
                java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, i);
                preparedStatement.setString(2, "Name " + i);
                preparedStatement.execute();
                preparedStatement.close();
            }

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
