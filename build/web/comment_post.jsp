<%@page import="com.blog.dao.*, com.blog.entities.*, com.blog.db.*"%>
<%
    User user = (User) session.getAttribute("currentUser");

    String postIdParam = request.getParameter("postId");
    String content = request.getParameter("content");

    if (postIdParam == null || postIdParam.trim().isEmpty()) {
        out.println("<h3>Error: postId missing</h3>");
        return;
    }

    int postId = Integer.parseInt(postIdParam);

    Comment c = new Comment();
    c.setUserId(user.getId());
    c.setPostId(postId);
    c.setContent(content);

    CommentDao dao = new CommentDao(DBConnection.getConnection());
    boolean success = dao.addComment(c);

    if (!success) {
        out.println("<h3>Error: Failed to add comment</h3>");
        return;
    }

    response.sendRedirect("view_post.jsp?id=" + postId);
%>

