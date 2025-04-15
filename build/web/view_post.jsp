<%@ page import="com.blog.dao.*, com.blog.entities.*, com.blog.db.*" %>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    int postId = Integer.parseInt(request.getParameter("id"));

    PostDao postDao = new PostDao(DBConnection.getConnection());
    Post post = postDao.getPostById(postId);

    User currentUser = (User) session.getAttribute("currentUser");
    if (currentUser == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    // Fetch comments
    CommentDao commentDao = new CommentDao(DBConnection.getConnection());
    List<Comment> comments = commentDao.getCommentsByPostId(postId);

    // Check like
    LikeDao likeDao = new LikeDao(DBConnection.getConnection());
    boolean liked = likeDao.hasUserLikedPost(currentUser.getId(), postId);
    int totalLikes = likeDao.countLikesOnPost(postId);
%>

<html>
<head>
    <title><%= post.getTitle() %></title>
    <link rel="stylesheet" href="css/view_post.css">
</head>
<body>
    <div class="container">

        <!-- 🔙 Back Button -->
        <a href="allpost.jsp" style="display: inline-block; margin-bottom: 15px; padding: 8px 16px; background-color: #007bff; color: white; text-decoration: none; border-radius: 4px;">
            &larr; Back to All Posts
        </a>

        <h2><%= post.getTitle() %></h2>
        <p><%= post.getContent() %></p>

        <!-- Like Section -->
        <form action="like_post.jsp" method="post">
            <input type="hidden" name="postId" value="<%= postId %>"/>
            <button type="submit"><%= liked ? "❤️ Liked" : "🤍 Like" %></button> 
            (<%= totalLikes %> Likes)
        </form>

        <!-- Comment Form -->
        <form action="comment_post.jsp" method="post">
            <input type="hidden" name="postId" value="<%= postId %>"/>
            <textarea name="content" placeholder="Write your comment..." required></textarea>
            <button type="submit">Comment</button>
        </form>

        <!-- Show comments -->
        <h4>Comments:</h4>
        <% if (comments.isEmpty()) { %>
            <p>No comments yet. Be the first to comment!</p>
        <% } else { %>
            <ul>
                <% for(Comment c : comments) { %>
                    <li><strong><%= c.getUserName() %>:</strong> <%= c.getContent() %></li>
                <% } %>
            </ul>
        <% } %>
    </div>
</body>
</html>

