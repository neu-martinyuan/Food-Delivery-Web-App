<%-- 
    Document   : driver-success
    Created on : 2021-4-6, 16:35:25
    Author     : Lenovo
--%>

<%@page import="com.mycompany.finalproject.pojo.Driver"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Driver driver = (Driver) request.getSession().getAttribute("driver");
        %>
        <h1>${requestScope.successMessage}</h1>
        <!--<a href="/HibernateDemo/driver/login.htm?username=<%=driver.getUsername()%>&password=<%=driver.getPassword()%>">Back to home page</a>-->
        <form action="/HibernateDemo/driver/login.htm" method="POST">
            <input type="hidden" name="username" value="<%=driver.getUsername()%>">
            <input type="hidden" name="password" value="<%=driver.getPassword()%>">
            <input type="submit" value="Back To Home Page">
        </form>
    </body>
</html>
