package com.insert;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class WithoutTransactionTest {

    String sql1 = "insert into person(id,name) values (1,'Andrei')";
    String sql2 = "insert into person(id,name) values (2,'Mihai')";
    String sql3 = "insert into person(id,name) values (2,'Valentin')";

    @Test
    void countNumberOfRowsAffected() throws SQLException {
        Transaction transaction = new Transaction();
        assertEquals(2, transaction.countNumberOfRowsAffected(sql1, sql2, sql3));
    }
}