<%-- 
    Document   : success
    Created on : Mar 25, 2021, 10:17:54 PM
    Author     : dedhi
--%>

<%@page import="com.mycompany.finalproject.pojo.Restaurant"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Restaurant res = (Restaurant) request.getSession().getAttribute("restaurant");
        %>
        <h1>${requestScope.successMessage}</h1>
        <!--<a href="/HibernateDemo/restaurant/login.htm?username=<%=res.getUsername()%>&password=<%=res.getPassword()%>">Back to home page</a>-->
        <form action="/HibernateDemo/restaurant/login.htm" method="POST">
            <input type="hidden" name="username" value="<%=res.getUsername()%>">
            <input type="hidden" name="password" value="<%=res.getPassword()%>">
            <input type="submit" value="Back To Home Page">
        </form>
    </body>
</html>