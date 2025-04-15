<%@page import="java.util.*, com.blog.dao.*, com.blog.db.*, com.blog.entities.Post, com.blog.entities.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    // 🔐 Session Check
    User currentUser = (User) session.getAttribute("currentUser");

    if (currentUser == null || !"admin".equals(currentUser.getRole())) {
        response.sendRedirect("login.jsp?msg=unauthorized");
        return;
    }

    PostDao postDao = new PostDao(DBConnection.getConnection());
    List<Post> postList = postDao.getAllPosts();
%>

<!DOCTYPE html>
<html>
<head>
    <title>Manage Posts</title>
    <style>
        body { font-family: Arial; padding: 20px; background-color: #f2f2f2; }
        h2 { color: #333; }
        table {
            width: 100%;
            border-collapse: collapse;
            background: white;
        }
        th, td {
            padding: 10px;
            border: 1px solid #ccc;
            text-align: left;
        }
        a.delete {
            color: red;
            text-decoration: none;
            font-weight: bold;
        }
        a.back {
            display: inline-block;
            margin-top: 20px;
            padding: 8px 12px;
            background-color: #1976d2;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
        a.back:hover {
            background-color: #0d47a1;
        }
    </style>
</head>
<body>

    <h2>📝 All Blog Posts</h2>

    <table>
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Content</th>
            <th>User ID</th>
            <th>Action</th>
        </tr>

        <%
            for (Post post : postList) {
        %>
        <tr>
            <td><%= post.getId() %></td>
            <td><%= post.getTitle() %></td>
            <td><%= post.getContent() %></td>
            <td><%= post.getUserId() %></td>
            <td>
                <a class="delete" href="DeletePostServlet?id=<%= post.getId() %>">🗑 Delete</a>
            </td>
        </tr>
        <%
            }
        %>
    </table>

    <a class="back" href="admin_dashboard.jsp">⬅ Back to Dashboard</a>

</body>
</html>

