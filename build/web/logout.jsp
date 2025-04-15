<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    // Invalidate session
    session.invalidate();
    
    // Redirect to login page
    response.sendRedirect("login.jsp?msg=loggedout");
%>
