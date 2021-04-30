<%-- 
    Document   : add-car-form
    Created on : 2021-4-19, 21:28:23
    Author     : Lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Car Form</title>
    </head>
    <body>
        <h2>Posting a New Car</h2>
        
        <form:form modelAttribute="car">
            <table>
                <tr>
                    <td>Car Make:</td>
                    <td><form:input path="make" size="30" /> <font color="red"><form:errors path="make"/></font></td>
                </tr>
                <tr>
                    <td>Car Plate</td>
                    <td><form:input path="plate" size="30" /> <font color="red"><form:errors path="plate"/></font></td>
                </tr>
                <tr>
                    <td>Car Color</td>
                    <td><form:input path="color" size="30" /> <font color="red"><form:errors path="color"/></font></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" value="Post Car" /></td>
                </tr>
            </table>
        </form:form>
        
        
    </body>
</html>
