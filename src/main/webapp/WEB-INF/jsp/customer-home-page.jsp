<%-- 
    Document   : customer-home-page
    Created on : 2021-4-3, 23:32:39
    Author     : Lenovo
--%>

<%@page import="com.mycompany.finalproject.pojo.Driver"%>
<%@page import="com.mycompany.finalproject.dao.CarDAO"%>
<%@page import="com.mycompany.finalproject.dao.DBConnection"%>
<%@page import="com.mycompany.finalproject.pojo.Car"%>
<%@page import="com.mycompany.finalproject.pojo.Order"%>
<%@page import="java.util.List"%>
<%@page import="com.mycompany.finalproject.pojo.Restaurant"%>
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
            List<Restaurant> restaurants = (List<Restaurant>) request.getSession().getAttribute("restaurants");
        %>
        <%
            Customer customer = (Customer) request.getSession().getAttribute("customer");
        %>
        Customer ID: <%=customer.getId() %>
        <br/>
        Username: <%=customer.getUsername()%>
        <br/>
        First Name: <%=customer.getFirstName()%>
        <br/>
        Last Name: <%=customer.getLastName()%>
        <br/>
        Age: <%=customer.getAge()%>
        <br/>
        Email Address: <%=customer.getEmail()%>
        <br/>
        Delivery Address: <%=customer.getAddress()%>
        <br/>
        Phone Number: <%=customer.getPhone_number()%>
        <br/>
        
        <a href="/HibernateDemo/customer-edit.html">Edit Your Account</a>
        
        <br/>
        
        <form action="/HibernateDemo/customer/chooserestaurant.htm" method="GET">
            Choose Restaurant:
            <select name="res_id">
                <%
                    for (int i=0; i < restaurants.size(); i++) {
                    %>
                    <option value="<%=restaurants.get(i).getRestaurant_id() %>"><%=restaurants.get(i).getRestaurant_name() %>   Address: <%=restaurants.get(i).getAddress()%></option>
                <%
                    }
                %>
            </select>
            <br/>
            <input type="submit" value="Submit">
        </form>
            
        <table border ="1" width="500" align="center"> 
         <tr bgcolor="00FF7F"> 
          <th><b>Order ID</b></th> 
          <th><b>Restaurant username</b></th> 
          <th><b>Restaurant address</b></th>
          <th><b>Restaurant phone number</b></th>
          
          <th><b>Driver username</b>
          <th><b>Driver phone number</b>
          
          <th><b>Car Make</b></th> 
          <th><b>Car Plate</b></th>
          <th><b>Car Color</b></th>
          
          <th><b>Dishes' ID</b></th> 
          <th><b>Food Price</b></th>
          <th><b>Dilivery Price</b></th>
         </tr> 
        <%
            List<Order> ordersByCustomer = (List<Order>) request.getSession().getAttribute("ordersByCustomer");
            DBConnection db = new DBConnection();
            CarDAO cardao = new CarDAO();
            for (Order order : ordersByCustomer) {
                
                Restaurant rest = db.getRestaurant(order.getRestaurant().getRestaurant_id());
                
                String driverUsername = null;
                String driverPhoneNumber = null;
                if (order.getDriver() != null) {
                    driverUsername = db.getDriver(order.getDriver().getId()).getUsername();    
                    driverPhoneNumber = db.getDriver(order.getDriver().getId()).getPhone_number(); 
                }
                
                String make = null;
                String plate = null;
                String color = null;
                if (order.getCar() != null) {
                    make = cardao.getCarById(order.getCar().getId()).getMake();
                    plate = cardao.getCarById(order.getCar().getId()).getPlate();
                    color = cardao.getCarById(order.getCar().getId()).getColor();
                }
                if (make == null) {
        %>
        <tr>
        <%
                } else {
        %>
        <tr bgcolor="red">
        <%
                }
        %>
        
                <td><%=order.getId()%></td>
                <td><%=rest.getUsername() %></td>
                <td><%=rest.getAddress() %></td>
                <td><%=rest.getPhone_number() %></td>
                
                
                <td><%=driverUsername %></td>
                <td><%=driverPhoneNumber %></td>
                
                
                <td><%=make %></td>
                <td><%=plate %></td>
                <td><%=color %></td>
                
                <td><%=order.getDishes() %></td>
                <td><%=order.getFood_price() %></td>
                <td><%=order.getDelivery_fee()%></td>
        </tr>
        <%}%> 
        </table> 
    </body>
</html>
