<%
    if (session == null || session.getAttribute("currentUser") == null) {
        response.sendRedirect("login.jsp?msg=unauthorized");
        return;
    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.blog.entities.User" %>
<%@page import="com.blog.entities.Post" %>
<%@page import="com.blog.dao.PostDao" %>
<%@page import="com.blog.helpers.ConnectionProvider" %>
<%@page import="java.util.List" %>

<%
    User user = (User) session.getAttribute("currentUser");
    PostDao postDao = new PostDao(ConnectionProvider.getConnection());
    List<Post> posts = postDao.getAllPosts();
%>

<!DOCTYPE html>
<html>
<head>
    <title>Home - Blog System</title>
    <link rel="stylesheet" href="css/home.css">
</head>
<body>
    <div class="container">
        <div class="header">
            <h2>👋 Welcome, <%= user.getName() %>!</h2>
            <p>You have successfully logged in.</p>
        </div>

        <div class="button-group">
            <form action="newpost.jsp" method="get">
                <button class="btn">➕ Create New Post</button>
            </form>
            <form action="allpost.jsp" method="get">
                <button class="btn">📄 View All Posts</button>
            </form>
            <form action="LogoutServlet" method="get">
                <button class="btn logout">🚪 Logout</button>
            </form>
        </div>

        <hr/>

        <h3>Your Blog Posts:</h3>

        <% boolean hasPosts = false;
            if (posts != null && !posts.isEmpty()) {
                for (Post post : posts) {
                    if (post.getUserId() == user.getId()) {
                        hasPosts = true; %>
                        <div class="post">
                            <h3><%= post.getTitle() %></h3>
                            <p><%= post.getContent() %></p>
                            <div class="post-actions">
                                <a href="edit_post.jsp?id=<%= post.getId() %>" class="link edit">️ Edit</a>
                                <a href="DeletePostServlet?id=<%= post.getId() %>" class="link delete"> Delete</a>
                            </div>
                        </div>
        <%          }
                }
            }
            if (!hasPosts) { %>
            <p>No blog posts available.</p>
        <% } %>
    </div>
</body>
</html>


