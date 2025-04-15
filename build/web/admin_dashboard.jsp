<%@page import="java.util.*, com.blog.dao.*, com.blog.db.*, com.blog.entities.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    // 🔐 Session Check
    User currentUser = (User) session.getAttribute("currentUser");

    if (currentUser == null || !"admin".equals(currentUser.getRole())) {
        response.sendRedirect("login.jsp?msg=unauthorized");
        return;
    }

    // ✅ DAO Setup
    PostDao postDao = new PostDao(DBConnection.getConnection());
    UserDao userDao = new UserDao(DBConnection.getConnection());

    int totalPosts = 0;
    int totalUsers = 0;

    try {
        totalPosts = postDao.getAllPosts().size();
        totalUsers = userDao.getAllUsers().size();
    } catch (Exception e) {
        e.printStackTrace();
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <style>
        body {
            font-family: Arial;
            padding: 20px;
            background-color: #f4f4f4;
        }
        h1 {
            color: #333;
        }
        .summary {
            margin: 20px 0;
            padding: 15px;
            background-color: #e0e0e0;
            border-radius: 8px;
        }
        .links a {
            display: inline-block;
            margin: 10px 10px 0 0;
            padding: 10px 15px;
            background-color: #1976d2;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
        .links a:hover {
            background-color: #0d47a1;
        }
    </style>
</head>
<body>

    <h1>👑 Admin Dashboard</h1>

    <div class="summary">
        <p><strong>Total Users:</strong> <%= totalUsers %></p>
        <p><strong>Total Posts:</strong> <%= totalPosts %></p>
    </div>

    <div class="links">
        <a href="admin_users.jsp">👥 Manage Users</a>
        <a href="admin_posts.jsp">📝 Manage Posts</a>
        <a href="logout.jsp">🚪 Logout</a>
    </div>

</body>
</html>
