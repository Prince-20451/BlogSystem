package com.blog.servlets;

import com.blog.dao.PostDao;
import com.blog.helpers.ConnectionProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/DeletePostServlet")
public class DeletePostServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // 🔍 Get the post ID from the request
            int id = Integer.parseInt(request.getParameter("id"));

            // ✅ DAO setup
            Connection con = ConnectionProvider.getConnection();
            PostDao postDao = new PostDao(con);

            // 🔥 Attempt to delete the post
            boolean success = postDao.deletePost(id);

            if (success) {
                response.sendRedirect("allpost.jsp?msg=deleted");
            } else {
                response.sendRedirect("allpost.jsp?msg=error");
            }

        } catch (NumberFormatException e) {
            response.sendRedirect("allpost.jsp?msg=invalid_id");
        } catch (IOException e) {
            response.sendRedirect("allpost.jsp?msg=exception");
        }
    }
}

