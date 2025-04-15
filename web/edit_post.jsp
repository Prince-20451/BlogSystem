<%@ page import="com.blog.entities.Post" %>
<%@ page import="com.blog.dao.PostDao" %>
<%@ page import="com.blog.helpers.ConnectionProvider" %>
<%
    String postIdStr = request.getParameter("id");
    int postId = Integer.parseInt(postIdStr);

    PostDao postDao = new PostDao(ConnectionProvider.getConnection());
    Post post = postDao.getPostById(postId);

    if (post == null) {
        out.println("<h3>Post not found!</h3>");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Edit Post</title>
    <link rel="stylesheet" href="css/editpost.css">
</head>
<body>

    <div class="container">
        <h2> Edit Blog Post</h2>

        <form action="EditPostServlet" method="post">
            <input type="hidden" name="id" value="<%= post.getId() %>" />

            <label>Title:</label>
            <input type="text" name="title" value="<%= post.getTitle() %>" required />

            <label>Content:</label>
            <textarea name="content" rows="8" required><%= post.getContent() %></textarea>

            <button type="submit">Update Post</button>
        </form>

        <div class="back-link">
            <a href="home.jsp"> Back to Home</a>
        </div>
    </div>

</body>
</html>
