package com.blog.servlets;

import com.blog.dao.UserDao;
import com.blog.entities.User;
import com.blog.db.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1️⃣ Form se data le rahe hain
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // 2️⃣ User object banaye
        User user = new User(name, email, password);

        // 3️⃣ Database connection & DAO
        Connection con = DBConnection.getConnection();
        UserDao userDao = new UserDao(con);

        // 4️⃣ Register user
        boolean isSaved = userDao.saveUser(user);

        if (isSaved) {
            // ✅ Registration successful → Redirect to login page
            response.sendRedirect("login.jsp?success=1");
        } else {
            // ❌ Registration failed → Redirect back with error
            response.sendRedirect("register.jsp?error=1");
        }
    }
}
