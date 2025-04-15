package com.blog.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection con;

    public static Connection getConnection() {
        if (con == null) {
            try {
                // 1️⃣ Load MySQL JDBC Driver
                Class.forName("com.mysql.cj.jdbc.Driver");

                // 2️⃣ Database Connection Details
                String url = "jdbc:mysql://localhost:3306/blog_db";  // Database Name: blog_db
                String user = "root";    // ✅ MySQL Username
                String pass = "@Princegarg_1";  // ✅ MySQL Password (yahan apna password likho)

                // 3️⃣ Establish Connection
                con = DriverManager.getConnection(url, user, pass);
                System.out.println("✅ Database Connection Successful!");

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                System.out.println("❌ Database Connection Failed: " + e.getMessage());
            }
        }
        return con;
    }
}
