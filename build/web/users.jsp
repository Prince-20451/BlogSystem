<%@ page import="java.util.*, com.blog.dao.*, com.blog.entities.*, com.blog.db.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    User currentUser = (User) session.getAttribute("currentUser");
    if (currentUser == null || !currentUser.getEmail().equals("admin@example.com")) {
        response.sendRedirect("login.jsp?msg=not_admin");
        return;
    }

    UserDao userDao = new UserDao(DBConnection.getConnection());
    List<User> userList = userDao.getAllUsers();
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>All Users</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 20px;
        }
        h2 {
            text-align: center;
        }
        table {
            width: 80%;
            margin: auto;
            border-collapse: collapse;
        }
        th, td {
            padding: 12px;
            border: 1px solid #ddd;
            text-align: center;
        }
        th {
            background-color: #f57c00;
            color: white;
        }
    </style>
</head>
<body>
    <h2>All Registered Users</h2>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Registered At</th>
        </tr>
        <%
            for (User user : userList) {
        %>
        <tr>
            <td><%= user.getId() %></td>
            <td><%= user.getName() %></td>
            <td><%= user.getEmail() %></td>
            <td><%= user.getCreatedAt() %></td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>

