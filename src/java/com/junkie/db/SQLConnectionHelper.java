/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.junkie.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ashish.yetre
 */
public class SQLConnectionHelper {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/attendance_system_db";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static Connection getNewConnection() throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        System.out.println("Connection Established");
        return connection;
    }

    public static void closedConnection(Connection connection) throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}
