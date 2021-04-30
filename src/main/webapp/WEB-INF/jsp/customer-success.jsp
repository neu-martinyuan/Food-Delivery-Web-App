<%-- 
    Document   : customer-success
    Created on : 2021-4-4, 17:07:59
    Author     : Lenovo
--%>

<%@page import="com.mycompany.finalproject.pojo.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Customer customer = (Customer) request.getSession().getAttribute("customer");
        %>
        <h1>${requestScope.successMessage}</h1>
        <!--<a href="/HibernateDemo/customer/login.htm?username=<%=customer.getUsername()%>&password=<%=customer.getPassword()%>">Back to home page</a>-->
        <form action="/HibernateDemo/customer/login.htm" method="POST">
            <input type="hidden" name="username" value="<%=customer.getUsername()%>">
            <input type="hidden" name="password" value="<%=customer.getPassword()%>">
            <input type="submit" value="Back To Home Page">
        </form>
    </body>
</html>
