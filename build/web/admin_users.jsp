<%@page import="java.util.*, com.blog.dao.*, com.blog.db.*, com.blog.entities.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    User currentUser = (User) session.getAttribute("currentUser");
    if (currentUser == null || !"admin".equals(currentUser.getRole())) {
        response.sendRedirect("login.jsp?msg=unauthorized");
        return;
    }

    UserDao userDao = new UserDao(DBConnection.getConnection());
    List<User> userList = userDao.getAllUsers();
%>

<!DOCTYPE html>
<html>
<head>
    <title>Manage Users</title>
</head>
<body>

    <!-- 🔔 Optional Message -->
    <%
        String msg = request.getParameter("msg");
        if ("deleted".equals(msg)) {
    %>
        <p style="color: green;">✅ User deleted successfully!</p>
    <%
        } else if ("error".equals(msg)) {
    %>
        <p style="color: red;">❌ Error deleting user!</p>
    <%
        } else if ("invalid".equals(msg)) {
    %>
        <p style="color: orange;">⚠️ Invalid request!</p>
    <%
        }
    %>

    <h1>👥 Manage Users</h1>
    <table border="1">
        <tr>
            <th>ID</th><th>Name</th><th>Email</th><th>Role</th><th>Action</th>
        </tr>
        <%
            for (User u : userList) {
                if (!"admin".equals(u.getRole())) {
        %>
        <tr>
            <td><%= u.getId() %></td>
            <td><%= u.getName() %></td>
            <td><%= u.getEmail() %></td>
            <td><%= u.getRole() %></td>
            <td>
                <!-- ✅ Better UX using link -->
                <a href="DeleteUserServlet?id=<%= u.getId() %>" onclick="return confirm('Are you sure you want to delete this user?');">🗑️ Delete</a>
            </td>
        </tr>
        <%
                }
            }
        %>
    </table>
</body>
</html>
