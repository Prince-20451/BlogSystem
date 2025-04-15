package com.blog.servlets;

import com.blog.db.DBConnection; // Apni DB connection class import ki
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/test-db") // ✅ FIX: Correct URL mapping
public class TestDBServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        System.out.println("🔹 Servlet Started!");

        try (PrintWriter out = response.getWriter()) {
            // ✅ Database Connection Test
            Connection conn = DBConnection.getConnection();
            
            if (conn != null) {
                out.println("<h1 style='color:green;'>✅ Database Connection Successful!</h1>");
                System.out.println("✅ Connection Successful!");
            } else {
                out.println("<h1 style='color:red;'>❌ Database Connection Failed!</h1>");
                System.out.println("❌ Connection Failed!");
            }
            out.flush();  
        } catch (Exception e) {  // ✅ FIX: SQLException hata ke general Exception handle kiya
            e.printStackTrace();
            throw new ServletException("❌ Database Connection Error!", e);
        }
    }
}

