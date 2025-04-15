<%
    if (session == null || session.getAttribute("currentUser") == null) {
        response.sendRedirect("login.jsp?msg=unauthorized");
        return;
    }
%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.blog.entities.User" %>
<%
    User user = (User) session.getAttribute("currentUser");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Create New Post</title>
    <link rel="stylesheet" href="css/newpost.css">
</head>
<body>
    <div class="container">
        <h1>📝 Create New Blog Post</h1>
        
        <form action="AddPostServlet" method="post">
            <label>Title:</label>
            <input type="text" name="title" required>

            <label>Content:</label>
            <textarea name="content" rows="8" required></textarea>

            <button type="submit">🚀 Submit Post</button>
        </form>

        <div class="back-link">
            <a href="home.jsp">🏠 Back to Home</a>
        </div>
    </div>
</body>
</html>
