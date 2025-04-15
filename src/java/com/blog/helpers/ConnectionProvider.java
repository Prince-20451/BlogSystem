package com.blog.helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
    private static Connection con;

    public static Connection getConnection() {
        if (con == null) {
            try {
                // Load the MySQL JDBC Driver
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Create the connection
                con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/blog_db", // your DB name
                    "root",                               // your DB username
                    "@Princegarg_1"                       // your DB password
                );

            } catch (ClassNotFoundException | SQLException e) {
            }
        }
        return con;
    }
}
