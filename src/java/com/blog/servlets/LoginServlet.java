package com.blog.servlets;

import com.blog.db.DBConnection;
import com.blog.entities.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        // 1️⃣ Get user credentials
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        try {
            // 2️⃣ Database connection
            Connection con = DBConnection.getConnection();

            // 3️⃣ Query to check user credentials
            String query = "SELECT * FROM users WHERE email = ? AND password = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, email);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // ✅ User exists, create session
                HttpSession session = request.getSession();
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));  // ✅ Set role

                session.setAttribute("currentUser", user);

                // 🔁 Redirect based on role
                if ("admin".equals(user.getRole())) {
                    response.sendRedirect("admin_dashboard.jsp");
                } else {
                    response.sendRedirect("home.jsp");
                }

            } else {
                // ❌ Invalid login
                out.println("<h3 style='color:red;'>Invalid Email or Password!</h3>");
                request.getRequestDispatcher("login.jsp").include(request, response);
            }

        } catch (SQLException e) {
            out.println("<h3 style='color:red;'>Something went wrong! " + e.getMessage() + "</h3>");
        }
    }
}
