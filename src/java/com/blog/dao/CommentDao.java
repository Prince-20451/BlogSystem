package com.blog.dao;

import com.blog.entities.Comment;
import java.sql.*;
import java.util.*;

public class CommentDao {
    private final Connection con;

    public CommentDao(Connection con) {
        this.con = con;
    }

    // Add a new comment
    public boolean addComment(Comment comment) {
        String query = "INSERT INTO comments(user_id, post_id, content) VALUES (?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, comment.getUserId());
            ps.setInt(2, comment.getPostId());
            ps.setString(3, comment.getContent());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // logging the error
            return false;
        }
    }

    // Get all comments for a specific post with user names
    public List<Comment> getCommentsByPostId(int postId) {
        List<Comment> list = new ArrayList<>();
        String query = "SELECT c.*, u.name FROM comments c INNER JOIN users u ON c.user_id = u.id WHERE c.post_id=? ORDER BY c.created_at DESC";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, postId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Comment c = new Comment();
                c.setId(rs.getInt("id"));
                c.setUserId(rs.getInt("user_id"));
                c.setPostId(rs.getInt("post_id"));
                c.setContent(rs.getString("content"));
                c.setCreatedAt(rs.getTimestamp("created_at"));
                c.setUserName(rs.getString("name")); // fetched from joined user table
                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // logging the error
        }
        return list;
    }
}
