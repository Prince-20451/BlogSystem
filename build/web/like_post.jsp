<%@page import="com.blog.dao.*, com.blog.db.*, com.blog.entities.*"%>
<%
    User user = (User) session.getAttribute("currentUser");

    String postIdParam = request.getParameter("postId");
    if (postIdParam == null || postIdParam.trim().isEmpty()) {
        out.println("<h3>Error: postId missing</h3>");
        return;
    }

    int postId = Integer.parseInt(postIdParam);

    LikeDao likeDao = new LikeDao(DBConnection.getConnection());
    likeDao.addLike(user.getId(), postId);

    response.sendRedirect("view_post.jsp?id=" + postId); // ? correct id param
%>

