<%-- 
    Document   : car-success
    Created on : 2021-4-19, 22:04:25
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
        <h1>${requestScope.successMessage}</h1>
        <%
            Driver driver = (Driver) request.getSession().getAttribute("driver");
        %>
        <form action="/HibernateDemo/driver/login.htm" method="POST">
            <input type="hidden" name="username" value="<%=driver.getUsername()%>">
            <input type="hidden" name="password" value="<%=driver.getPassword()%>">
            <input type="submit" value="Back To Home Page">
        </form>
    </body>
</html>
