package com.blog.dao;

import java.sql.*;

public class LikeDao {
    private final Connection con;

    public LikeDao(Connection con) {
        this.con = con;
    }

    // ✅ Add Like
    public boolean addLike(int userId, int postId) {
        String query = "INSERT INTO likes(user_id, post_id) VALUES (?, ?)";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, userId);
            ps.setInt(2, postId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            return false; // Already liked or some error
        }
    }

    // ✅ Count Likes
    public int countLikesByPost(int postId) {
        String query = "SELECT COUNT(*) FROM likes WHERE post_id=?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, postId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // ✅ Check if user already liked
    public boolean hasUserLikedPost(int userId, int postId) {
        String query = "SELECT * FROM likes WHERE user_id = ? AND post_id = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, userId);
            ps.setInt(2, postId);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // returns true if a record exists
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // ✅ Count Likes (Alternate name)
    public int countLikesOnPost(int postId) {
        return countLikesByPost(postId);
    }
}
