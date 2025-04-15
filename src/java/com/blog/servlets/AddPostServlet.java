package com.blog.servlets;

import com.blog.dao.PostDao;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.helpers.ConnectionProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

public class AddPostServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Check for logged-in user
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("currentUser") == null) {
            response.sendRedirect("login.jsp"); // redirect to login if not logged in
            return;
        }

        // Get post details
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        // Get user from session
        User user = (User) session.getAttribute("currentUser");

        // Create Post object
        Post post = new Post(title, content, user.getId());

        // Save post using DAO
        PostDao dao = new PostDao(ConnectionProvider.getConnection());
        boolean success = dao.savePost(post);

        // Redirect accordingly
        if (success) {
            response.sendRedirect("allpost.jsp");
        } else {
            response.getWriter().println("❌ Failed to add post!");
        }
    }
}

