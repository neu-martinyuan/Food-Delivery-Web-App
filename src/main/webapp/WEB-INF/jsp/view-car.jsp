<%-- 
    Document   : view-car
    Created on : 2021-4-19, 23:39:45
    Author     : Lenovo
--%>

<%@page import="com.mycompany.finalproject.pojo.Driver"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table border="1" cellpadding="5" cellspacing="5">
            <tr>
                <td><b>Make</b></td>
                <td><b>Plate</b></td>
                <td><b>Color</b></td>
            </tr>
            <c:forEach var="car" items="${cars}">
                <tr>
                    <td>${car.make}</td>
                    <td>${car.plate}</td>
                    <td>${car.color}</td>
                </tr>
            </c:forEach>
        </table>      
        </br>
        
        <h1>Select Car ID</h1>
        <form action="/HibernateDemo/driver/choosecar.htm" method="POST">
            <select name="chosencar">
                <c:forEach var="car" items="${cars}">
                    <option value="${car.id}">${car.id}</option>
                </c:forEach>
            </select>
            <input type="submit" value="Select Car">
        </form>
        
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
