<%
    if (session == null || session.getAttribute("currentUser") == null) {
        response.sendRedirect("login.jsp?msg=unauthorized");
        return;
    }
%>

<%@page import="java.util.List"%>
<%@page import="com.blog.dao.PostDao"%>
<%@page import="com.blog.entities.Post"%>
<%@page import="com.blog.db.DBConnection"%>

<%
    String query = request.getParameter("query");
    PostDao dao = new PostDao(DBConnection.getConnection());
    List<Post> posts;

    if (query != null && !query.trim().isEmpty()) {
        posts = dao.searchPosts(query);
    } else {
        posts = dao.getAllPosts();
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>All Blog Posts</title>
    <link rel="stylesheet" href="css/allpost.css">
</head>
<body>

    <div class="container">
        <h1>? All Blog Posts</h1>

        <!-- ? Search Bar -->
        <form method="get" action="allpost.jsp" class="search-bar">
            <input type="text" name="query" placeholder="Search posts by title or content..." 
                   value="<%= query != null ? query : "" %>">
            <button type="submit">? Search</button>
        </form>

        <div class="nav-links">
            <a href="home.jsp">? Home</a>
            <a href="newpost.jsp">? Add New Post</a>
        </div>
        <hr>

        <%
            if (posts != null && !posts.isEmpty()) {
                for(Post post : posts){
        %>
            <div class="post-card">
                <h2><%= post.getTitle() %></h2>
                <p><%= post.getContent().length() > 250 ? post.getContent().substring(0, 250) + "..." : post.getContent() %></p>
                <div class="meta">
                    ? ID: <%= post.getId() %> &nbsp; | &nbsp; ? User ID: <%= post.getUserId() %> &nbsp; | &nbsp; ? <%= post.getCreatedAt() %>
                </div>
                <div class="post-actions">
                    <a href="edit_post.jsp?id=<%= post.getId() %>">?? Edit</a>
                    <a href="DeletePostServlet?id=<%= post.getId() %>" class="delete-link">?? Delete</a>
                    <a href="view_post.jsp?id=<%= post.getId() %>" class="read-link">? Read More</a>
                </div>
            </div>
        <%
                }
            } else {
        %>
            <p class="no-posts">? No posts found!</p>
        <%
            }
        %>
    </div>

</body>
</html>
