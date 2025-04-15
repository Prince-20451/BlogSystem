package com.blog.dao;

import com.blog.entities.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private final Connection con;

    // Constructor: Accepts a DB connection
    public UserDao(Connection con) {
        this.con = con;
    }

    // ✅ 1. Register a new user
    public boolean saveUser(User user) {
        boolean success = false;
        String query = "INSERT INTO users(name, email, password, role) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getRole() != null ? user.getRole() : "user"); // Default role = user

            int rows = pstmt.executeUpdate();
            success = (rows > 0);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    // ✅ 2. Authenticate user for login
    public User getUserByEmailAndPassword(String email, String password) {
        User user = null;
        String query = "SELECT * FROM users WHERE email=? AND password=?";

        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, email);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setCreatedAt(rs.getTimestamp("created_at"));
                user.setRole(rs.getString("role")); // 👈 Add this line to fetch role
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    // ✅ 3. Fetch all registered users (for admin panel)
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";

        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setCreatedAt(rs.getTimestamp("created_at"));
                user.setRole(rs.getString("role")); // 👈 Include role

                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    // ✅ 4. Delete user by ID
    public boolean deleteUserById(int id) {
        String query = "DELETE FROM users WHERE id=?";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }
}


