package com.blog.servlets;

import com.blog.dao.PostDao;
import com.blog.db.DBConnection;
import com.blog.entities.Post;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "EditPostServlet", urlPatterns = {"/EditPostServlet"}) // ✅ Add this!
public class EditPostServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        Connection con = DBConnection.getConnection();
        PostDao postDao = new PostDao(con);

        Post updatedPost = new Post(id, title, content, 0, null); 
        boolean success = postDao.updatePost(updatedPost);

        if (success) {
            response.sendRedirect("home.jsp?msg=Post Updated Successfully");
        } else {
            response.sendRedirect("edit_post.jsp?id=" + id + "&error=Failed to Update");
        }
    }
}

