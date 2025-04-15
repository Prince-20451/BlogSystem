
package com.blog.servlets;

import com.blog.dao.UserDao;
import com.blog.db.DBConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int userId = Integer.parseInt(request.getParameter("id"));

            UserDao dao = new UserDao(DBConnection.getConnection());
            boolean success = dao.deleteUserById(userId);

            if (success) {
                response.sendRedirect("admin_users.jsp?msg=deleted");
            } else {
                response.sendRedirect("admin_users.jsp?msg=error");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("admin_users.jsp?msg=exception");
        }
    }
}
